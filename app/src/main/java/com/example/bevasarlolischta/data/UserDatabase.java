package com.example.bevasarlolischta.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, FoodItem.class, ShoppingList.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract  ShoppingListDao shDao();

    public abstract FoodItemDao fiDao();
}
