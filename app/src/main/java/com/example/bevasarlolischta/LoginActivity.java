package com.example.bevasarlolischta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.bevasarlolischta.data.UserDatabase;
import com.example.bevasarlolischta.fragments.LoginFragment;
import com.example.bevasarlolischta.fragments.RegisterFragment;

public class LoginActivity extends AppCompatActivity {

    public Button register_btn;
    public Button login_btn;
    public static UserDatabase probauser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register_btn = (Button) findViewById(R.id.register);
        login_btn = (Button) findViewById(R.id.login);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                RegisterFragment registerFragment = new RegisterFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, registerFragment).addToBackStack(null).commit();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                LoginFragment loginFragment = new LoginFragment();
                fragmentManager.beginTransaction().replace(R.id.fragment_container, loginFragment).addToBackStack(null).commit();
            }
        });

        probauser = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "userdb").allowMainThreadQueries().build();

    }

    @Override
    public void onBackPressed(){

            super.onBackPressed();
        }

}
