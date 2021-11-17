package com.example.songify;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ReproductorActivity extends AppCompatActivity {

    private TextView nombreCancion;
    private TextView nombreArtista;
    private ImageView ivcaratula;
    private String url_cancion;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        String titulo = getIntent().getStringExtra("TITULO");
        String artista = getIntent().getStringExtra("ARTISTA");
        String caratula = getIntent().getStringExtra("CARATULA");

        if (titulo == null) {
            titulo = "";
        }
        if (artista == null) {
            artista = "";
        }
        if (caratula == null) {
            caratula = "";
        }


        nombreCancion = findViewById(R.id.tv_nombreCancion);
        nombreCancion.setText(titulo);

        nombreArtista = findViewById(R.id.tv_nombreArtista);
        nombreArtista.setText(artista);

        ivcaratula = findViewById(R.id.caratula_default);
        Glide.with(this).load(caratula).into(ivcaratula);

    }
}