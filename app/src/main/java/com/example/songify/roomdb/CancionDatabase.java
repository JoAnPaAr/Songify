package com.example.songify.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Cancion.class}, version = 1)
public abstract class CancionDatabase extends RoomDatabase {

    private static CancionDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CancionDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CancionDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, CancionDatabase.class, "canciones.db").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract CancionDAO getDao();

}
