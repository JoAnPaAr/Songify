package com.example.songify;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.songify.model.Cancion;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Reproductor extends AppCompatActivity {
    private Cancion cancion;
    private TextView nombreCancion;
    private TextView nombreArtista;
    private FloatingActionButton atras;
    private FloatingActionButton adelante;
    private FloatingActionButton play;
    private FloatingActionButton stop;
    private FloatingActionButton repeat;
    private FloatingActionButton fav;
    private boolean repetir = false;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reproductor);

        init();
    }

    private void init() {
        this.nombreCancion = this.findViewById(R.id.tv_nombreCancion);
        this.nombreArtista = this.findViewById(R.id.tv_nombreArtista);
        this.atras = this.findViewById(R.id.boton_atras);
        this.adelante = this.findViewById(R.id.boton_adelante);
        this.play = this.findViewById(R.id.boton_play);
        this.stop = this.findViewById(R.id.boton_stop);
        this.repeat = this.findViewById(R.id.boton_repeat);
        this.fav = this.findViewById(R.id.boton_favorito);
    }

    public boolean isRepetir() {
        return repetir;
    }

    public void setRepetir(boolean repetir) {
        this.repetir = repetir;
    }


    public void ColorBotonFav(){
        if(this.cancion.isFavorito()){
            fav.setBackgroundResource(R.drawable.img_star);
            Toast.makeText(this, "Eliminado de Favoritos", Toast.LENGTH_SHORT).show();

        }else{
            fav.setBackgroundResource(R.drawable.img_star_on);
            Toast.makeText(this, "Favorito", Toast.LENGTH_SHORT).show();
        }
    }

    public void PlayPause (View view){
        if (cancion.isReproduciendo()){
            //En caso de estar reproduciendo una canción, se pausará
            //pause
            play.setBackgroundResource(R.drawable.img_pause);
            Toast.makeText(this, "Pausa", Toast.LENGTH_SHORT).show();

        }else{
            //En caso de no estar reproduciendo una canción, la reproduce
            //play
            play.setBackgroundResource(R.drawable.img_play);
            Toast.makeText(this, "Reproduciendo", Toast.LENGTH_SHORT).show();
        }
    }

    public void repetir (){
        //En caso de estar activa la repetición, la cancela
        if(isRepetir()){
            //no repetir
            repeat.setBackgroundResource(R.drawable.img_repeat);
            Toast.makeText(this, "No repetir", Toast.LENGTH_SHORT).show();
            //En caso de estar desactivada la repetición, la activa
        }else{
            //repetir
            repeat.setBackgroundResource(R.drawable.img_repeat_on);
            Toast.makeText(this, "Repetir", Toast.LENGTH_SHORT).show();
        }
    }
}
