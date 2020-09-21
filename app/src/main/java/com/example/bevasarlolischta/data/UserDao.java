package com.example.bevasarlolischta.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bevasarlolischta.data.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void register (User user);

    @Query("SELECT * FROM users WHERE userid = :id")
    User getUserById (String id);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    User getUserFromLogin (String username, String password);
}
