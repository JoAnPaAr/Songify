package com.example.songify.retrofit;

import com.example.songify.AppExecutors;
import com.example.songify.roomdb.Cancion;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class CancionLoaderRunnable implements Runnable {

    private final InputStream mInFile;
    private final OnCancionesLoadedListener mOnCancionesLoadedListener;

    public CancionLoaderRunnable(InputStream inFile, OnCancionesLoadedListener onCancionesLoadedListener) {
        mInFile = inFile;
        mOnCancionesLoadedListener = onCancionesLoadedListener;
    }

    @Override
    public void run() {
        //Carga los datos
        //Parse json file into JsonReader
        JsonReader reader = new JsonReader(new InputStreamReader(mInFile));
        // Parse JsonReader into list of Repo using Gson
        List<Cancion> listaCanciones = Arrays.asList(new Gson().fromJson(reader, Cancion[].class));

        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                mOnCancionesLoadedListener.onCancionesLoaded(listaCanciones);

            }
        });
    }
}
