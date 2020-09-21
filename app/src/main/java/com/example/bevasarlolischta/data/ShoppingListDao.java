package com.example.bevasarlolischta.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShoppingListDao {

    @Insert
    public void AddItem (ShoppingList shoppingList);

    @Query("SELECT * FROM shoppinglist WHERE name = :name" )
    ShoppingList getListByName(String name);

    @Query("SELECT * FROM SHOPPINGLIST WHERE userId = :userId")
    List<ShoppingList> getListByUserId(int userId);

    @Query("SELECT * FROM shoppinglist")
    List<ShoppingList> getAllLists();
}
