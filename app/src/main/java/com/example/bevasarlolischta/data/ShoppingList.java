package com.example.bevasarlolischta.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Random;

@Entity(tableName = "shoppinglist")
public class ShoppingList {

    @PrimaryKey
    @ColumnInfo (name = "listid")
    private int listID;

    @ColumnInfo
    private String name;

    @Ignore
    private List<FoodItem>  listitem;


    public int userId;

    public ShoppingList(){};

    public ShoppingList(String name, List<FoodItem> listitem, int userId) {
        Random rng = new Random();
        this.listID = rng.nextInt(99999);
        this.name = name;
        this.listitem = listitem;
        this.userId = userId;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodItem> getListitem() {
        return listitem;
    }

    public void setListitem(List<FoodItem> listitem) {
        this.listitem = listitem;
    }


}
