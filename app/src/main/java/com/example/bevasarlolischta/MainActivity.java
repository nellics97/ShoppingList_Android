package com.example.bevasarlolischta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Database;
import androidx.room.Room;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bevasarlolischta.adapters.ListAdapter;
import com.example.bevasarlolischta.data.ShoppingList;
import com.example.bevasarlolischta.data.User;
import com.example.bevasarlolischta.data.UserDatabase;
import com.example.bevasarlolischta.fragments.CreateListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListAdapter.OnListListener {

    public static User loggedInUser = null;
    static public Button register_login;
    static public Button new_list_button;
    static List<ShoppingList> ShoppingListsArray;
    static ArrayList<String> lists = new ArrayList<>();
    public static UserDatabase database;

    public void openLogActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "shoppinglist").allowMainThreadQueries().build();
        register_login = (Button) findViewById(R.id.reg_log);
        new_list_button = (Button) findViewById(R.id.create_new_list);
        this.UpdateLists();

        RecyclerView recyclerview = findViewById(R.id.list_of_lists);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter adapter = new ListAdapter( lists, this);
        adapter.setClickListener(this);
        recyclerview.setAdapter(adapter);


        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogActivity();
                String u = null;
            }

        });



        new_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loggedInUser != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    CreateListFragment createListFragment = new CreateListFragment();
                    fragmentManager.beginTransaction().replace(R.id.main_activity, createListFragment).addToBackStack(null).commit();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Log in first please " ,Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onListClick(View view, int position) {
        String listName = lists.get(position);
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("listName", listName);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.UpdateLists();
    }

    public static void UpdateLists()
    {
        if (loggedInUser == null) {
            new_list_button.setVisibility(View.GONE);
            lists.clear();

        }
        else {
            register_login.setVisibility(View.GONE);

        if(lists != null) {
            lists.clear();
        }
        ShoppingListsArray = database.shDao().getListByUserId(loggedInUser.getUserid());
        for ( ShoppingList item: ShoppingListsArray
        ) {
            lists.add(item.getName());

        }

        }
    }


}
