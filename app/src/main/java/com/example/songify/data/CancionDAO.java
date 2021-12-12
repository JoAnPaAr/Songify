package com.example.songify.data;

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

    //Obtiene todas las canciones
    @Query("SELECT * FROM cancion")
    LiveData<List<Cancion>> getAllCanciones();

    //Obtiene las canciones favoritas
    @Query("SELECT * FROM cancion WHERE fav = 1 ORDER BY titulo DESC")
    LiveData<List<Cancion>> getAllFavorites();

    //Obtiene las canciones ordenadas por el ranking
    @Query("SELECT * FROM cancion ORDER BY ranking ASC")
    LiveData<List<Cancion>>  showRanking();

    //Cuenta las canciones que hay en la BD
    @Query("SELECT count(*) FROM cancion")
    int getNumberCanciones();

    //Inserta en la base de datos una cancion
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCancion(Cancion cancion);

    //Inserta en la base de datos una lista de canciones
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Cancion> cancions);

    //Borra una cancion
    @Delete
    void delete(Cancion cancion);

    //Borra todas las canciones
    @Query("DELETE FROM cancion")
    void deleteAll();

    //Actualiza la informacion de la cancion
    @Update
    int update(Cancion cancion);

    @Query("SELECT * FROM cancion WHERE id LIKE :id")
    Cancion getCancionPorID(String id);
}
