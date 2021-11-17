//package com.example.songify.ui.reproductor;
//
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//
//import com.example.songify.R;
//import com.example.songify.roomdb.Cancion;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Reproductor#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class Reproductor extends Fragment {
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private Cancion cancion;
//    private TextView nombreArtista;
//    private FloatingActionButton atras;
//    private FloatingActionButton adelante;
//    private FloatingActionButton play;
//    private FloatingActionButton stop;
//    private FloatingActionButton repeat;
//    private FloatingActionButton fav;
//    private MediaPlayer mediaPlayer;
//
//    public Reproductor() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Reproductor.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Reproductor newInstance(String param1, String param2) {
//        Reproductor fragment = new Reproductor();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//
//        }
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View vista = inflater.inflate(R.layout.fragment_reproductor, container, false);
//        init(vista);
//        return vista;
//    }
//
//    private void init(View vista) {
//        this.nombreCancion = vista.findViewById(R.id.tv_cancion_titulo);
//        this.nombreArtista = vista.findViewById(R.id.tv_nombreArtista);
//        this.atras = vista.findViewById(R.id.boton_atras);
//        this.adelante = vista.findViewById(R.id.boton_adelante);
//        this.play = vista.findViewById(R.id.boton_play);
//        this.stop = vista.findViewById(R.id.boton_stop);
//        this.repeat = vista.findViewById(R.id.boton_repeat);
//        this.fav = vista.findViewById(R.id.boton_favorito);
//
//    }
//
//    public void ColorBotonFav() {
//        if (this.cancion.isFavorito()) {
//            fav.setBackgroundResource(R.drawable.img_star);
//            Toast.makeText(getContext(), "Eliminado de Favoritos", Toast.LENGTH_SHORT).show();
//
//        } else {
//            fav.setBackgroundResource(R.drawable.img_star_on);
//            Toast.makeText(getContext(), "Favorito", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void PlayPause(View view) {
//        if (true) {
//            //En caso de estar reproduciendo una canción, se pausará
//            //pause
//            play.setBackgroundResource(R.drawable.img_pause);
//            Toast.makeText(getContext(), "Pausa", Toast.LENGTH_SHORT).show();
//
//        } else {
//            //En caso de no estar reproduciendo una canción, la reproduce
//            //play
//            play.setBackgroundResource(R.drawable.img_play);
//            Toast.makeText(getContext(), "Reproduciendo", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void repetir() {
//        //En caso de estar activa la repetición, la cancela
//        if (true) {
//            //no repetir
//            //En caso de estar desactivada la repetición, la activa
//        } else {
//            //repetir
//        }
//    }
//}