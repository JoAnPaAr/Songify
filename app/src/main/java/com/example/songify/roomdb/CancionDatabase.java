package com.example.songify.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cancion.class}, version = 1)
public abstract class CancionDatabase extends RoomDatabase {

    private static CancionDatabase INSTANCE;

    public static CancionDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, CancionDatabase.class, "DB_CANCIONES.db").build();
        }
        return INSTANCE;
    }

    public abstract CancionDAO getDao();

}
