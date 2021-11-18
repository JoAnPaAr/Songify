package com.example.songify;

import com.example.songify.roomdb.Cancion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("JoAnPaAr/Songify/master/app/src/main/res/raw/playlistexport.json")
    Call<ArrayList<Cancion>> getCanciones();
}
