package com.example.songify.retrofit;

import com.example.songify.AppExecutors;
import com.example.songify.MainActivity;
import com.example.songify.roomdb.Cancion;
import com.example.songify.roomdb.CancionDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CancionesNetworkLoaderRunnable implements Runnable {

    private final OnCancionesLoadedListener mOnCancionesLoadedListener;

    public CancionesNetworkLoaderRunnable(OnCancionesLoadedListener onCancionesLoadedListener) {
        mOnCancionesLoadedListener = onCancionesLoadedListener;
    }


    //
    //
    //        service.getAllCanciones().enqueue(new Callback<List<Cancion>>() {
    //            @Override
    //            public void onResponse(Call<List<Cancion>> call, Response<List<Cancion>> response) {
    //                mCancionViewModel.bulkInsert(response.body());
    //            }
    //
    //            @Override
    //            public void onFailure(Call<List<Cancion>> call, Throwable t) {
    //                t.printStackTrace();
    //            }
    //        });

    public void run(){
        //Inicia la instancia de retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CancionesService service = retrofit.create(CancionesService.class);
        service.getAllCanciones().enqueue(new Callback<List<Cancion>>() {
            @Override
            public void onResponse(Call<List<Cancion>> call, Response<List<Cancion>> response) {
               AppExecutors.getInstance().mainThread().execute(()->mOnCancionesLoadedListener.onCancionesLoaded(response.body()));
            }

            @Override
            public void onFailure(Call<List<Cancion>> call, Throwable t) {

            }
        });

    }

}
