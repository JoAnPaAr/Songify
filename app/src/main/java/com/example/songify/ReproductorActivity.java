package com.example.songify;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.songify.roomdb.Cancion;

import java.io.IOException;
import java.util.List;

public class ReproductorActivity extends AppCompatActivity {

    //Elementos del layout
    private TextView nombreCancion;
    private TextView nombreArtista;
    private ImageView ivcaratula;
    private ImageView atras;
    private ImageView adelante;
    private ImageView play;
    private ImageView stop;
    private ImageView repeat;
    private SeekBar seekBar;
    private TextView duracionActual;
    private TextView duracionTotal;


    //Variables de la activity
    private String url_cancion;
    private MediaPlayer mediaPlayer;
    private List<Cancion> listaCanciones;
    private boolean firstTime;
    Runnable runnable;
    Handler handler;
    int minutes, seconds;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        //Inicializando las variables con los valores proporcionados del intent
        String id = getIntent().getStringExtra("ID");
        String titulo = getIntent().getStringExtra("TITULO");
        String artista = getIntent().getStringExtra("ARTISTA");
        String caratula = getIntent().getStringExtra("CARATULA");
        url_cancion = getIntent().getStringExtra("URL");

        //Para cuando se actualice la aplicacion en tiempo de ejecucion
        //listaCanciones = (List<Cancion>) getIntent().getSerializableExtra("LIST");

        //En caso de que las variables no tengan contenido se les inicializa
        // a uno para evitar posibles errores
        if (id == null) {
            id = "";
        }
        if (titulo == null) {
            titulo = "";
        }
        if (artista == null) {
            artista = "";
        }
        if (caratula == null) {
            caratula = "";
        }
        if (url_cancion == null) {
            url_cancion = "";
        }
        //Esta variable se emplea para comenzar a reproducir el archivo en
        // el momento que se abre el reproductor
        firstTime = true;

        //Se inserta el nombre de la cancion que se ha pasado mediante intent
        nombreCancion = findViewById(R.id.tv_nombreCancion);
        nombreCancion.setText(titulo);

        //Se inserta el nombre del artista que se ha pasado mediante intent
        nombreArtista = findViewById(R.id.tv_nombreArtista);
        nombreArtista.setText(artista);

        //Se inserta la imagen de la cancion que se ha pasado mediante intent
        ivcaratula = findViewById(R.id.caratula_default);
        Glide.with(this).load(caratula).into(ivcaratula);

        //Vincular las variables con los botones del layout
        play = findViewById(R.id.boton_play);
        stop = findViewById(R.id.boton_stop);
        repeat = findViewById(R.id.boton_repeat);
        this.seekBar = (SeekBar) findViewById(R.id.seek_Bar);

        duracionActual = findViewById(R.id.tv_tiempo_actual);
        duracionTotal = findViewById(R.id.tv_tiempo_total);

        //Inicializar el mediaPlayer
        mediaPlayer = new MediaPlayer();

        //Invoca el metodo que se encarga de manejar los eventos del reproductor
        reproducir();

        handler = new Handler();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //Este metodo se encarga de manejar los eventos que puedan ocurrir en el reproductor
    private void reproducir() {

        //Obtiene el archivo que se va a reproducir
        try {
            mediaPlayer.setDataSource(url_cancion);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        //Se ejecuta en el momento que se ha obtenido el archivo y esta preparado para reproducirse
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mp.getDuration());
                minutes = mp.getDuration() / (60 * 1000);
                seconds = (mp.getDuration() / 1000) % 60;
                String str = String.format("%d:%02d", minutes, seconds);
                duracionTotal.setText(str);

                //En caso de que se haya abierto por primera vez la app comenzara la reproduccion.
                //En caso de haberse pulsado el stop no comenzara la reproduccion.
                if (firstTime) {
                    firstTime = false;
                    //Modifica el icono del boton play/pause
                    play.setBackgroundResource(R.drawable.img_pause);
                    //Comienza la reproduccion del archivo
                    mp.start();
                    updateSeekBar();
                }
            }
        });

        //En caso de pulsar el boton play/pause
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //En caso de que ya estuviese reproduciendo, se para la reproduccion
                // y se modifica el boton play/pause
                if (mediaPlayer.isPlaying()) {
                    play.setBackgroundResource(R.drawable.img_play);
                    mediaPlayer.pause();
                    //En caso de que no estuviese reproduciendo, se continua la reproduccion
                    // y se modifica el boton play/pause
                } else {
                    play.setBackgroundResource(R.drawable.img_pause);
                    mediaPlayer.start();
                    updateSeekBar();
                }
            }
        });

        //En caso de pulsar el boton stop
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Para la reproduccion y modifica el boton play/pause
                mediaPlayer.stop();
                play.setBackgroundResource(R.drawable.img_play);
                //Prepara el reproductor para poder volver a reproducir la cancion
                mediaPlayer.prepareAsync();
            }
        });

        //En caso de pulsar el boton repeat
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //En caso de estar marcado que se repita la cancion, se anula la
                // seleccion y se modifica el boton repeat
                if (mediaPlayer.isLooping()) {
                    repeat.setBackgroundResource(R.drawable.img_repeat);
                    mediaPlayer.setLooping(false);
                    Toast.makeText(view.getContext(), "No repetir", Toast.LENGTH_SHORT).show();

                    //En caso de no estar repitiendo la cancion, se marca que se repita
                    // la cancion y se modifica el boton repeat
                } else {
                    repeat.setBackgroundResource(R.drawable.img_repeat_on);
                    mediaPlayer.setLooping(true);
                    Toast.makeText(view.getContext(), "Repetir", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //Este metodo va actualizando a cada segundo la barra que indica el
    // progreso de la reproduccion de la cancion
    private void updateSeekBar() {
        int currPos = mediaPlayer.getCurrentPosition();
        seekBar.setProgress(currPos);
        minutes = mediaPlayer.getCurrentPosition() / (60 * 1000);
        seconds = (mediaPlayer.getCurrentPosition() / 1000) % 60;
        String str = String.format("%d:%02d", minutes, seconds);
        duracionActual.setText(str);
        runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
            }
        };
        handler.postDelayed(runnable, 1000);
    }

}