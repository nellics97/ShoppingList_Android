package com.example.bevasarlolischta.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bevasarlolischta.ListActivity;
import com.example.bevasarlolischta.LoginActivity;
import com.example.bevasarlolischta.MainActivity;
import com.example.bevasarlolischta.R;
import com.example.bevasarlolischta.data.User;
import com.example.bevasarlolischta.data.UserDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button log_btn;
    private EditText musername, mpassword;
    String username;
    String password;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        log_btn = view.findViewById(R.id.log_button);


        musername = view.findViewById(R.id.log_username);
        mpassword = view.findViewById(R.id.log_password);



        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = musername.getText().toString();
                password = mpassword.getText().toString();
                MainActivity.loggedInUser = LoginActivity.probauser.userDao().getUserFromLogin(username, password);

                if (MainActivity.loggedInUser != null) {
                    MainActivity.UpdateLists();
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(),"Welcome " + username,Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getActivity(),"Try again",Toast.LENGTH_SHORT).show();


            }
        });

        return view;
    }

}
