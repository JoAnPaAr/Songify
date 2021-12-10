package com.example.songify.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.songify.roomdb.Cancion;

import java.util.List;

public class CancionViewModel extends AndroidViewModel {
    private CancionRepository mRepository;
    private MutableLiveData<List<Cancion>> cancionMutableLiveData = new MutableLiveData<>();

    private final LiveData<List<Cancion>> mListaCanciones;
    private final LiveData<List<Cancion>> mListaFavoritos;
    private final LiveData<List<Cancion>> mListaExitos;

    public CancionViewModel(Application application) {
        super(application);
        mRepository = new CancionRepository(application);
        mListaCanciones = mRepository.getAllCanciones();
        mListaFavoritos = mRepository.getAllFavoritos();
        mListaExitos = mRepository.getAllExitos();
    }

    public void init() {
        mRepository.checkFetch();
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

    //Consultas a la base de datos
    public void insert(Cancion cancion) {
        mRepository.insert(cancion);
    }

    public void update(Cancion cancion) {
        mRepository.update(cancion);
    }

    public void borrar() {
        mRepository.borrar();
    }

    public void bulkInsert(List<Cancion> listaCanciones) {
        mRepository.bulkInsert(listaCanciones);
    }

}
