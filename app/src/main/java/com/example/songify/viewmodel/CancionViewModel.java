package com.example.songify.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.songify.repository.CancionRepository;
import com.example.songify.roomdb.Cancion;

import java.util.List;

public class CancionViewModel extends AndroidViewModel {
    private CancionRepository mRepository;
    private MutableLiveData<List<Cancion>> data = new MutableLiveData<>();

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
        return mRepository.getCancionPorID(id);
    }

    public void insert(Cancion cancion) {
        mRepository.insert(cancion);
    }

    public void update(Cancion cancion) {
        mRepository.update(cancion);
    }

    public void borrar() {
        mRepository.borrar();
    }

}
