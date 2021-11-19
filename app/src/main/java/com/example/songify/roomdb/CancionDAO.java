package com.example.songify.roomdb;

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
    public List<Cancion> getAllCanciones();

    @Query("SELECT * FROM cancion WHERE fav = 1")
    public List<Cancion> getAllFavorites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertCancion(Cancion cancion);

    @Delete
    public void delete(Cancion cancion);

    @Query("DELETE FROM cancion")
    public void deleteAll();

    @Update
    public int update(Cancion cancion);

    @Query("SELECT * FROM cancion ORDER BY ranking ASC")
    public  List<Cancion>  showRanking();

    @Query("SELECT * FROM cancion WHERE id LIKE :id")
    public Cancion getCancionPorID(String id);
}
