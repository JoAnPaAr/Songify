package com.example.songify.retrofit;

import com.example.songify.AppExecutors;
import com.example.songify.load.OnResponseLoadedListener;
import com.example.songify.roomdb.Cancion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CancionesNetworkLoaderRunnable implements Runnable {

    private final OnResponseLoadedListener mOnCancionesLoadedListener;
    private String BASE_URL = "https://raw.githubusercontent.com/";

    public CancionesNetworkLoaderRunnable(OnResponseLoadedListener onResponseLoadedListener) {
        mOnCancionesLoadedListener = onResponseLoadedListener;
    }

    public void run(){
        //Inicia la instancia de retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CancionesService service = retrofit.create(CancionesService.class);
        Call<List<Cancion>> call = service.getAllCanciones();
        try{
            Response<List<Cancion>> response = call.execute();
            List<Cancion> cancions = response.body() == null ? new ArrayList<>() : response.body();
            AppExecutors.getInstance().mainThread().execute(()->mOnCancionesLoadedListener.onResponseLoaded(cancions));

        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
