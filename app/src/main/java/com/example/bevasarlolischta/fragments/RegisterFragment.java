package com.example.bevasarlolischta.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bevasarlolischta.LoginActivity;
import com.example.bevasarlolischta.MainActivity;
import com.example.bevasarlolischta.R;
import com.example.bevasarlolischta.data.User;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {


    private Button reg_btn;
    private EditText musername, mpassword;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        reg_btn = view.findViewById(R.id.reg_button);
        reg_btn.setOnClickListener(this);

        musername = view.findViewById(R.id.new_username);
        mpassword = view.findViewById(R.id.new_password);

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String username = musername.getText().toString();
                String password = mpassword.getText().toString();
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setFridgeid("22A");





                Toast.makeText(getActivity(),"Welcome " + username,Toast.LENGTH_SHORT).show();

                LoginActivity.probauser.userDao().register(user);
                MainActivity.loggedInUser = LoginActivity.probauser.userDao().getUserFromLogin(username, password);
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }



}
