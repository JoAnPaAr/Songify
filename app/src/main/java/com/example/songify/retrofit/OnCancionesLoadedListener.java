package com.example.songify.retrofit;

import com.example.songify.roomdb.Cancion;

import java.util.List;

public interface OnCancionesLoadedListener {

    public void onCancionesLoaded(List<Cancion> listaCanciones);
}
