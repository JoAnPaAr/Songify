package com.example.songify.retrofit;

import com.example.songify.roomdb.Cancion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CancionesService {
    @GET("JoAnPaAr/Songify/master/app/src/main/res/raw/playlistexport.json")
    Call<List<Cancion>> getAllCanciones();
}
