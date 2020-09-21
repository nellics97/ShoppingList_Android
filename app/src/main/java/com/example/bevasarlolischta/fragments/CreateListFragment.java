package com.example.bevasarlolischta.fragments;


import android.app.LauncherActivity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.bevasarlolischta.MainActivity;
import com.example.bevasarlolischta.R;
import com.example.bevasarlolischta.data.FoodItem;
import com.example.bevasarlolischta.data.ShoppingList;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateListFragment extends Fragment {

    private Button create_button;
    private EditText mname;

    public CreateListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_list, container, false);
        create_button = view.findViewById(R.id.create_button_fragment);

        mname = view.findViewById(R.id.create_new_list_name);
        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newname = mname.getText().toString();
                ShoppingList shoppingList = new ShoppingList(newname, null, MainActivity.loggedInUser.getUserid());



                MainActivity.database.shDao().AddItem(shoppingList);

                List<ShoppingList> asd = MainActivity.database.shDao().getAllLists();
                ((MainActivity)getActivity()).UpdateLists();
                getActivity().onBackPressed();
            }
        });


        return view;
    }


}
