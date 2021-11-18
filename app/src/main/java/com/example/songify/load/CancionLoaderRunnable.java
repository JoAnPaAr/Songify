package com.example.songify.load;

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
    private final OnResponseLoadedListener mOnResponseLoadedListener;

    public CancionLoaderRunnable(InputStream inFile, OnResponseLoadedListener onResponseLoadedListener) {
        mInFile = inFile;
        mOnResponseLoadedListener = onResponseLoadedListener;
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
                mOnResponseLoadedListener.onResponseLoaded(listaCanciones);

            }
        });
    }
}
