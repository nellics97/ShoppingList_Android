package com.example.bevasarlolischta.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Random;

@Entity (tableName = "users")
public class User {

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "userid")
    private int userid;

    @ColumnInfo
    private String username;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private String fridgeid;

    public User() {}

    public User(int userid, String username, String password, String fridgeid) {
        //this.userid = userid;

        this.userid = new Random().nextInt(9999);
        this.username = username;
        this.password = password;
        this.fridgeid = fridgeid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFridgeid() {
        return fridgeid;
    }

    public void setFridgeid(String fridgeid) {
        this.fridgeid = fridgeid;
    }
}
