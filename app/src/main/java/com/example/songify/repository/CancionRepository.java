package com.example.songify.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.songify.AppExecutors;
import com.example.songify.roomdb.Cancion;
import com.example.songify.roomdb.CancionDAO;
import com.example.songify.roomdb.CancionDatabase;

import java.util.List;
import java.util.concurrent.Executor;

public class CancionRepository {

    private CancionDAO mCancionDao;
    private LiveData<List<Cancion>> mListaCanciones;
    private LiveData<List<Cancion>> mListaFavoritos;
    private LiveData<List<Cancion>> mListaExitos;

    public CancionRepository(Application application) {
        CancionDatabase db = CancionDatabase.getInstance(application);
        mCancionDao = db.getDao();
        mListaCanciones = mCancionDao.getAllCanciones();
        mListaFavoritos = mCancionDao.getAllFavorites();
        mListaExitos = mCancionDao.showRanking();
    }

    public LiveData<List<Cancion>> getAllCanciones() {
        return mListaCanciones;
    }

    public LiveData<List<Cancion>> getAllFavoritos() {
        return mListaFavoritos;
    }

    public LiveData<List<Cancion>> getAllExitos() {
        return mListaExitos;
    }

    public Cancion getCancionPorID(String id) {
        //return CancionDatabase.databaseWriterExecutor.execute(()->mCancionDao.getCancionPorID(id));
        return mCancionDao.getCancionPorID(id);
    }

    public void insert(Cancion cancion) {
        CancionDatabase.databaseWriterExecutor.execute(() -> {
            mCancionDao.insertCancion(cancion);
        });
    }

    public void update(Cancion cancion) {
        CancionDatabase.databaseWriterExecutor.execute(() -> {
            mCancionDao.update(cancion);
        });
    }

    public void borrar() {
        CancionDatabase.databaseWriterExecutor.execute(() -> {
            mCancionDao.deleteAll();
        });
    }
}
