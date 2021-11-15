package com.example.songify.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CancionDAO {

    @Query("SELECT * FROM cancion")
    public List<Cancion> getAllCanciones();

    @Query("SELECT * FROM cancion WHERE isFavorito = 'TRUE'")
    public List<Cancion> getAllFavorites();

    @Insert
    public void insertCancion(Cancion cancion);

    @Delete
    public void delete(Cancion cancion);

    @Query("DELETE FROM cancion")
    public void deleteAll();

    @Update
    public int update(Cancion cancion);
}
