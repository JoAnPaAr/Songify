package com.example.songify.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.songify.AppExecutors;
import com.example.songify.retrofit.CancionesNetworkDataSource;
import com.example.songify.retrofit.CancionesService;
import com.example.songify.roomdb.Cancion;
import com.example.songify.roomdb.CancionDAO;
import com.example.songify.roomdb.CancionDatabase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.jvm.functions.FunctionN;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CancionRepository {

    private static CancionRepository sInstance;
    private CancionDAO mCancionDao;
    private LiveData<List<Cancion>> mListaCanciones;
    private LiveData<List<Cancion>> mListaFavoritos;
    private LiveData<List<Cancion>> mListaExitos;
    private long lastUpdateTimeMillis;

    public CancionRepository(Application application) {
        //Obtiene la instancia de la base de datos
        CancionDatabase db = CancionDatabase.getInstance(application);
        mCancionDao = db.getDao();
        mListaCanciones = mCancionDao.getAllCanciones();
        mListaFavoritos = mCancionDao.getAllFavorites();
        mListaExitos = mCancionDao.showRanking();
        lastUpdateTimeMillis = System.currentTimeMillis();
    }

    //Obtiene los datos de la red
    public void callRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CancionesService service = retrofit.create(CancionesService.class);
        service.getAllCanciones().enqueue(new Callback<List<Cancion>>() {
            @Override
            public void onResponse(Call<List<Cancion>> call, Response<List<Cancion>> response) {
                bulkInsert(response.body());
            }

            @Override
            public void onFailure(Call<List<Cancion>> call, Throwable t) {
            }
        });
    }

    //Comprueba si es necesario buscar datos en la red
    public void checkFetch() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            if (isFetchNeeded()) {
                doFetchCanciones();
            }
        });
    }

    //Devuelve true en caso de estar la base de datos vacia
    private boolean isFetchNeeded() {
        Long lastFetchTimeMillis = lastUpdateTimeMillis;
        lastFetchTimeMillis = lastFetchTimeMillis == null ? 0L : lastFetchTimeMillis;
        long timeFromLastFetch = System.currentTimeMillis() - lastFetchTimeMillis;
        //Implement cache policy: When time has passed or no canciones in cache
        return mCancionDao.getNumberCanciones() == 0;
    }

    //Realiza la carga de los datos desde la red en otro hilo
    public void doFetchCanciones() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            callRetrofit();
            lastUpdateTimeMillis = System.currentTimeMillis();
        });
    }

    //Obtiene todas las canciones
    public LiveData<List<Cancion>> getAllCanciones() {
        return mListaCanciones;
    }

    //Obtiene todas las canciones favoritas
    public LiveData<List<Cancion>> getAllFavoritos() {
        return mListaFavoritos;
    }

    //Obtiene todas las canciones ordenadas por exitos
    public LiveData<List<Cancion>> getAllExitos() {
        return mListaExitos;
    }

    //Consultas a la base de datos
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

    public void bulkInsert(List<Cancion> listaCanciones) {
        CancionDatabase.databaseWriterExecutor.execute(() -> {
            mCancionDao.bulkInsert(listaCanciones);
        });
    }

}
