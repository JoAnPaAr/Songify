package com.example.songify.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.songify.AppExecutors;
import com.example.songify.retrofit.CancionesNetworkLoaderRunnable;
import com.example.songify.roomdb.Cancion;

import java.util.List;

public class CancionesNetworkDataSource {
    private static CancionesNetworkDataSource sInstance;

    private final MutableLiveData<List<Cancion>> mDownloadedCanciones;

    private CancionesNetworkDataSource(){
        mDownloadedCanciones = new MutableLiveData<>();
    }

    public synchronized static CancionesNetworkDataSource getInstance(){
        if (sInstance == null){
            sInstance = new CancionesNetworkDataSource();
        }
        return sInstance;
    }

    public LiveData<List<Cancion>> getCurrentCanciones(){ return mDownloadedCanciones;}

    public void fetchCanciones(){
      //AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(username, repos -> mDownloadedRepos.postValue(repos.toArray(new Repo[0]))));
        AppExecutors.getInstance().networkIO().execute(new CancionesNetworkLoaderRunnable(listaCanciones -> mDownloadedCanciones.postValue(listaCanciones)));
    }
}
