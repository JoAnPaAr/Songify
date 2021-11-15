package com.example.songify.ui.favoritos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songify.R;
import com.example.songify.RecyclerViewAdapter;
import com.example.songify.roomdb.Cancion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Favoritos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favoritos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerFavoritos;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<Cancion> listaFavoritos;

    public Favoritos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Favoritos.
     */
    // TODO: Rename and change types and number of parameters
    public static Favoritos newInstance(String param1, String param2) {
        Favoritos fragment = new Favoritos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_lista_favoritos, container, false);

        listaFavoritos = new ArrayList<Cancion>();
        fillListaFavoritos();
        initRecyclerViewFavoritos(vista);

        return vista;
    }

    //Se inicia la recyclerview
    private void initRecyclerViewFavoritos(View vista) {
        recyclerFavoritos = vista.findViewById(R.id.rv_Favoritos);
        recyclerFavoritos.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerFavoritos.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(listaFavoritos, getContext());
        recyclerFavoritos.setAdapter(mAdapter);
    }

    //Carga las canciones que se encuentren en la base de datos marcadas como favoritas
//    private void fillListaFavoritos() {
//        CancionDatabase db = CancionDatabase.getInstance(getContext());
//        listaFavoritos = db.getDao().getAllFavorites();
//    }

    private void fillListaFavoritos() {
        Cancion c0 = new Cancion("0", "Cancion2", "Blur", "180", "https://img.discogs.com/SIySlohaBvKNM722OvT7NGpZxUg=/fit-in/600x600/filters:strip_icc():format(jpeg):mode_rgb():quality(90)/discogs-images/R-385550-1266624470.jpeg.jpg");
        Cancion c1 = new Cancion("1", "Giants", "BeckyG", "230", "https://elrework.com/wp-content/uploads/2019/11/true-damage.jpg");
        Cancion c2 = new Cancion("2", "Pretty Fly", "Offspring", "300", "https://image.api.playstation.com/cdn/EP0006/BLES00228_00/6uTuRMgsl2znREnwsFv8jr7g33XENX1N.png");
        Cancion c3 = new Cancion("3", "Let's Go", "Stuck to the Sound", "240", "https://i1.sndcdn.com/artworks-000353762163-7bo5aj-t500x500.jpg");
        Cancion c4 = new Cancion("4", "Taking Over", "LeagueOfLegends", "290", "https://m.media-amazon.com/images/I/91ApZlV-GBL._SS500_.jpg");
        Cancion c5 = new Cancion("5", "Star", "Lil Cake", "230", "https://s.mxmcdn.net/images-storage/albums5/6/4/3/4/0/6/56604346_500_500.jpg");
        Cancion c6 = new Cancion("6", "Onion", "PinnochioP", "90", "https://i1.sndcdn.com/artworks-000576487046-fdjb9g-t500x500.jpg");
        Cancion c7 = new Cancion("7", "Reloaded Installer #13", "LHSchiptunes", "217", "https://i.ytimg.com/vi/CYql_6MRNPU/sddefault.jpg");
        Cancion c8 = new Cancion("8", "Lost Woods", "The Legend of Zelda", "120", "https://i.ytimg.com/vi/7RbdY_hcUAA/0.jpg");

        listaFavoritos.addAll(Arrays.asList(new Cancion[]{c0, c1, c2, c3, c4, c5, c6, c7, c8}));
    }
}