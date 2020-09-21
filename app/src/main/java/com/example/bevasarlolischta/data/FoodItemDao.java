package com.example.bevasarlolischta.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FoodItemDao {

    @Insert
    public void addFoodItem(FoodItem foodItem);

    @Query("SELECT * FROM fooditem WHERE listid = :listId")
    public List<FoodItem> getAllFoodItemByListId(int listId);

    @Query("SELECT * FROM fooditem WHERE name = :name")
    public FoodItem getFoodItemByName(String name);

    @Query("SELECT * FROM fooditem")
    public List<FoodItem>  getAllFoodItems();

    @Query("SELECT * FROM fooditem WHERE fooditemid = :id" )
    FoodItem getitemById(int id);

    @Query("UPDATE fooditem SET name = :name, quantity = :quantity, unit = :unit WHERE fooditemid = :id")
    int updateFooditem(int id, String name, int quantity, String unit);

    @Delete
    public void deleteItem (FoodItem foodItem);

}
