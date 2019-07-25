package com.duongnv.bikerental.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.duongnv.bikerental.dao.CartDAO;
import com.duongnv.bikerental.model.Cart;

@Database(entities = {Cart.class}, exportSchema = false, version = 1)
public abstract class CartDatabase extends RoomDatabase {
    private static final String DB_NAME = "Cart";
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), CartDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration().build();

        }
        return instance;
    }

    public abstract CartDAO cartDAO();
}
