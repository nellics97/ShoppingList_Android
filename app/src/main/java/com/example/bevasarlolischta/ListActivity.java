package com.example.bevasarlolischta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bevasarlolischta.adapters.FoodItemAdapter;
import com.example.bevasarlolischta.adapters.ListAdapter;
import com.example.bevasarlolischta.data.FoodItem;
import com.example.bevasarlolischta.data.ShoppingList;
import com.example.bevasarlolischta.data.UserDatabase;
import com.example.bevasarlolischta.fragments.CreateListFragment;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements FoodItemAdapter.OnListListener {

    private static ShoppingList currentList;
    private static List<FoodItem> foodItemList;
    ArrayList<String> items = new ArrayList<>();
    static UserDatabase databaseShoppingList;
    static UserDatabase databaseFoodItem;

    private static  ArrayList<String> names =  new ArrayList<>();
    private static ArrayList<String> numbers =  new ArrayList<>();
    private static ArrayList<String> units =  new ArrayList<>();
    private static RecyclerView recyclerview;
    public static FoodItemAdapter adapter;

 public Button add_new_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        databaseShoppingList = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "shoppinglist").allowMainThreadQueries().build();
        databaseFoodItem = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "fooditem").allowMainThreadQueries().build();
        Intent intent = getIntent();

            currentList = databaseShoppingList.shDao().getListByName(intent.getStringExtra("listName"));

        UpdateFoodItems();

        add_new_button = (Button) findViewById(R.id.add_new_item);
        add_new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListitemsActivity();
            }
        });


        recyclerview = findViewById(R.id.food_items_list);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FoodItemAdapter( names, numbers, units, this);
        adapter.setClickListener(this);
        recyclerview.setAdapter(adapter);


        //TEST/////////////////////////////////////
        List<ShoppingList> shoppingLists = databaseShoppingList.shDao().getAllLists();
        List<FoodItem> food = databaseFoodItem.fiDao().getAllFoodItems();

        //TEST/////////////////////////////////////

    }

    public void openListitemsActivity() {
        Intent intent = new Intent(this, ListItemsActivity.class);
        intent.putExtra("listId", currentList.getListID());
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UpdateFoodItems();
    }

    public static void UpdateFoodItems()
    {
        if(names != null) {
            names.clear();
        }
        if(numbers != null) {
            numbers.clear();
        }
        if(units != null) {
            units.clear();
        }

        if(currentList != null)
        {
            foodItemList = databaseFoodItem.fiDao().getAllFoodItemByListId(currentList.getListID());
        }

        for (FoodItem item: foodItemList
        ) {
            names.add(item.name);
            numbers.add(String.valueOf(item.quantity));
            units.add(item.unit);

        }

    }



    @Override
    public void onListClick(View view, int position) {

        String foodItemName = names.get(position);
        Intent intent = new Intent(this, ListItemsActivity.class);
        intent.putExtra("foodItemName", foodItemName);

        startActivity(intent);

    }

}
