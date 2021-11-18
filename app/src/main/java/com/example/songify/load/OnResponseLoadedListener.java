package com.example.songify.load;

import com.example.songify.roomdb.Cancion;

import java.util.List;

public interface OnResponseLoadedListener {

    public void onResponseLoaded(List<Cancion> listaCanciones);
}
