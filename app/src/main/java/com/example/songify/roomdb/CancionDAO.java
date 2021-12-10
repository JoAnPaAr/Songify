package com.example.songify.roomdb;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CancionDAO {

    @Query("SELECT * FROM cancion")
    LiveData<List<Cancion>> getAllCanciones();

    @Query("SELECT * FROM cancion WHERE fav = 1")
    LiveData<List<Cancion>> getAllFavorites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCancion(Cancion cancion);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Cancion> cancions);

    @Delete
    void delete(Cancion cancion);

    @Query("DELETE FROM cancion")
    void deleteAll();

    @Update
    int update(Cancion cancion);

    @Query("SELECT * FROM cancion ORDER BY ranking ASC")
    LiveData<List<Cancion>>  showRanking();

    @Query("SELECT * FROM cancion WHERE id LIKE :id")
    Cancion getCancionPorID(String id);
}
