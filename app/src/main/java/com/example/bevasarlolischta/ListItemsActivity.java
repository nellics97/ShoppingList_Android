package com.example.bevasarlolischta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bevasarlolischta.data.FoodItem;
import com.example.bevasarlolischta.data.ShoppingList;
import com.example.bevasarlolischta.data.UserDatabase;

import java.util.List;

public class ListItemsActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener, NumberPicker.OnValueChangeListener{

    UserDatabase database;
    ImageView tick_img;
    EditText food_item_name;
    ImageView delete_img;
    FoodItem currentFoodItem = new FoodItem();
    int currentShoppingListid;
    boolean update = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Intent intent = getIntent();
        currentShoppingListid = intent.getIntExtra("listId", 0);

        database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "fooditem").allowMainThreadQueries().build();

        currentFoodItem = database.fiDao().getFoodItemByName(intent.getStringExtra("foodItemName"));




        food_item_name = findViewById(R.id.list_name);
        //NumberPicker
        final NumberPicker quantity = (NumberPicker) findViewById(R.id.numpick_list);
        quantity.setMinValue(1);
        quantity.setMaxValue(1000);
        quantity.setOnValueChangedListener(this);

        //Spinner
        final Spinner unit_spinner = (Spinner) findViewById(R.id.spinner_list);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unit_spinner.setAdapter(adapter);
        unit_spinner.setOnItemSelectedListener(this);


            if (currentFoodItem != null) {
                update = true;

                food_item_name.setText(currentFoodItem.name);

                quantity.setValue(currentFoodItem.quantity);

                if (currentFoodItem.unit.equals("pieces"))
                    unit_spinner.setSelection(0);
               else if (currentFoodItem.unit.equals("litres"))
                    unit_spinner.setSelection(1);
                else if (currentFoodItem.unit.equals("grams"))
                    unit_spinner.setSelection(2);

                int picked_number = quantity.getValue();
                String newname = food_item_name.getText().toString();
                String spinner = unit_spinner.getSelectedItem().toString();

                database.fiDao().updateFooditem(currentFoodItem.id, newname, picked_number, spinner);
            }
            else
            {
                currentFoodItem = new FoodItem();
            }




        tick_img = (ImageView) findViewById(R.id.tick);
        tick_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int picked_number = quantity.getValue();
                    String newname = food_item_name.getText().toString();
                    String spinner = unit_spinner.getSelectedItem().toString();
                    FoodItem foodItem = new FoodItem(newname, picked_number, spinner, currentShoppingListid, -1);

                    if (update){
                        database.fiDao().updateFooditem(currentFoodItem.id, newname, picked_number, spinner);
                    }
                    else {
                        database.fiDao().addFoodItem(foodItem);
                    }


                Toast toast =Toast.makeText(getApplicationContext(), "Item saved", Toast.LENGTH_LONG);
                toast.show();
               ListActivity.adapter.notifyDataSetChanged();
                ListActivity.UpdateFoodItems();
                onBackPressed();

            }
        });

        delete_img = (ImageView) findViewById(R.id.delete);
        delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.fiDao().deleteItem(currentFoodItem);
                ListActivity.adapter.notifyDataSetChanged();

                ListActivity.UpdateFoodItems();
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Toast toast = Toast.makeText(this, "Value is " + newVal, Toast.LENGTH_LONG);
        toast.show();
    }

}
