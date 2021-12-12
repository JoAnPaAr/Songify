package com.example.songify.data;

import com.example.songify.data.Cancion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CancionesService {
    @GET("JoAnPaAr/Songify/master/app/src/main/res/raw/playlistexport.json")
    Call<List<Cancion>> getAllCanciones();
}
