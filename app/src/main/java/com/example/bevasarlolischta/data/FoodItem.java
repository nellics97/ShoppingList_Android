package com.example.bevasarlolischta.data;

import android.app.LauncherActivity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Random;



@Entity(tableName = "fooditem")
public class FoodItem {

    @PrimaryKey
    @ColumnInfo(name = "fooditemid")
    public int id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public int quantity;

    @ColumnInfo
    public String unit;

    @ColumnInfo
    public int listid;

    @ColumnInfo
    public int fridgeid;

    public FoodItem(){}

    public FoodItem(String name, int quantity, String unit, int listid, int fridgeid) {
        Random rng = new Random();
        id = rng.nextInt(10000);
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
        this.listid = listid;
        this.fridgeid = fridgeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getlistidid() {
        return listid;
    }

    public void setlistidid(int listid) {
        this.id = listid;
    }
}
