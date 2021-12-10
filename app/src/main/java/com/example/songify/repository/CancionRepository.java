package com.example.songify.repository;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.songify.AppExecutors;
import com.example.songify.retrofit.CancionesService;
import com.example.songify.roomdb.Cancion;
import com.example.songify.roomdb.CancionDAO;
import com.example.songify.roomdb.CancionDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CancionRepository {

    private CancionDAO mCancionDao;
    private LiveData<List<Cancion>> mListaCanciones;
    private LiveData<List<Cancion>> mListaFavoritos;
    private LiveData<List<Cancion>> mListaExitos;
    private MutableLiveData<List<Cancion>> listaCancionLiveData = new MutableLiveData<>();

    public CancionRepository(Application application) {
        //Obtiene la instancia de la base de datos
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

    public void obtenerListaCanciones() {

    }
}
