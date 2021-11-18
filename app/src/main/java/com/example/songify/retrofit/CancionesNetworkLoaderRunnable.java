package com.example.songify.retrofit;

import com.example.songify.AppExecutors;
import com.example.songify.load.OnResponseLoadedListener;
import com.example.songify.roomdb.Cancion;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CancionesNetworkLoaderRunnable implements Runnable {

    private final OnResponseLoadedListener mOnCancionesLoadedListener;

    public CancionesNetworkLoaderRunnable(OnResponseLoadedListener onResponseLoadedListener) {
        mOnCancionesLoadedListener = onResponseLoadedListener;
    }

    @Override
    public void run() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Retrofit crea una instancia de la clase declarada previamente
        CancionesService service = retrofit.create(CancionesService.class);
        try {
            List<Cancion> canciones = service.getAllCanciones().execute().body();

            AppExecutors.getInstance().mainThread().execute(new Runnable() {
                @Override
                public void run() {
                    mOnCancionesLoadedListener.onResponseLoaded(canciones);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
