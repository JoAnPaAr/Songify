package com.example.songify.ui.canciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songify.AppExecutors;
import com.example.songify.R;
import com.example.songify.RecyclerViewAdapter;
import com.example.songify.ReproductorActivity;
import com.example.songify.roomdb.Cancion;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Canciones#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Canciones extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerCanciones;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Retrofit retrofit;
    List<Cancion> listaCanciones;

    public Canciones() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment listaCanciones.
     */
    // TODO: Rename and change types and number of parameters
    public static Canciones newInstance(String param1, String param2) {
        Canciones fragment = new Canciones();
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
        View vista = inflater.inflate(R.layout.fragment_lista_canciones, container, false);

        //En este metodo se invoca al metodo que vincula la RecyclerView con su layout
        initRecyclerViewCanciones(vista);

        return vista;
    }

//    private void initRetrofit() {
//        //Se proporciona informacion para la construccion de retrofit
//        retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//
//        Call<ArrayList<Cancion>> call = jsonPlaceHolderApi.getCanciones();
//
//        call.enqueue(new Callback<ArrayList<Cancion>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Cancion>> call, Response<ArrayList<Cancion>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getContext(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                for (Cancion song : response.body()) {
//                    listaCanciones.add(song);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Cancion>> call, Throwable t) {
//                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    //Se inicia la recyclerview
    private void initRecyclerViewCanciones(View vista) {

        //Se vincula el recyclerView con su layout correspondiente
        recyclerCanciones = vista.findViewById(R.id.rv_Cancion);
        recyclerCanciones.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(vista.getContext());
        recyclerCanciones.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(new ArrayList<>(), vista.getContext());


        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                //Parse json file into JsonReader
                JsonReader reader = new JsonReader(new InputStreamReader(getResources().openRawResource(R.raw.playlistexport)));
                // Parse JsonReader into list of Repo using Gson
                listaCanciones = Arrays.asList(new Gson().fromJson(reader, Cancion[].class));
                mAdapter.swap(listaCanciones);
            }
        });

        //Se crea el adapter de la RecyclerView
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Se almacena en estas variables los datos de la cancion seleccionada por el usuario
                final String id = listaCanciones.get(recyclerCanciones.getChildAdapterPosition(view)).getId().toString();
                //Se obtiene el contexto del main activity
                Intent intent = new Intent(view.getContext(),
                        ReproductorActivity.class);

                //Pasa el valor del id de la cancion seleccionada por el usuario
                intent.putExtra("ID", id);

                //Pasa la lista de canciones al reproductor
                intent.putExtra("LIST", (Serializable) listaCanciones);

                //Invoca la nueva activity
                startActivity(intent);
            }
        });
        recyclerCanciones.setAdapter(mAdapter);
    }

    //Carga las canciones que se encuentren en la base de datos
//    public void fillListaCancion() {
//
//        CancionDatabase db = CancionDatabase.getInstance(getContext());
//        if (db.getDao().getAllCanciones().isEmpty()) {
//        } else {
//            listaCanciones = db.getDao().getAllCanciones();
//
//        }
//    }

    //(String id, String title, String artist, String duration,String preview)
//    private void fillListaCancion() {
//        Cancion c0 = new Cancion("0000", "Cancion2", "Blur", "180", "https://img.discogs.com/SIySlohaBvKNM722OvT7NGpZxUg=/fit-in/600x600/filters:strip_icc():format(jpeg):mode_rgb():quality(90)/discogs-images/R-385550-1266624470.jpeg.jpg",
//                "https://p.scdn.co/mp3-preview/d99e6b974944ce051359f2ce26b2deba044a46aa?cid=7d6e6a3d46f443159acf4529c6a2dd06");
//        Cancion c1 = new Cancion("1", "Giants", "BeckyG", "230", "https://elrework.com/wp-content/uploads/2019/11/true-damage.jpg",
//                "https://p.scdn.co/mp3-preview/d11a4ca063cab7cbfbfbd5fa3732e977f4fc9300?cid=7d6e6a3d46f443159acf4529c6a2dd06");
//
//        Cancion c2 = new Cancion("2", "Pretty Fly", "Offspring", "300", "https://image.api.playstation.com/cdn/EP0006/BLES00228_00/6uTuRMgsl2znREnwsFv8jr7g33XENX1N.png");
//        Cancion c3 = new Cancion("3", "Let's Go", "Stuck to the Sound", "240", "https://i1.sndcdn.com/artworks-000353762163-7bo5aj-t500x500.jpg");
//        Cancion c4 = new Cancion("4", "Taking Over", "LeagueOfLegends", "290", "https://m.media-amazon.com/images/I/91ApZlV-GBL._SS500_.jpg");
//        Cancion c5 = new Cancion("5", "Star", "Lil Cake", "230", "https://s.mxmcdn.net/images-storage/albums5/6/4/3/4/0/6/56604346_500_500.jpg");
//        Cancion c6 = new Cancion("6", "Onion", "PinnochioP", "90", "https://i1.sndcdn.com/artworks-000576487046-fdjb9g-t500x500.jpg");
//        Cancion c7 = new Cancion("7", "Reloaded Installer #13", "LHSchiptunes", "217", "https://i.ytimg.com/vi/CYql_6MRNPU/sddefault.jpg");
//        Cancion c8 = new Cancion("8", "Lost Woods", "The Legend of Zelda", "120", "https://i.ytimg.com/vi/7RbdY_hcUAA/0.jpg",
//                "https://p.scdn.co/mp3-preview/c46c516a707f341073102b1b34d0431630100e5a?cid=7d6e6a3d46f443159acf4529c6a2dd06");
//
//
//        listaCanciones.addAll(Arrays.asList(new Cancion[]{c0, c1, c2, c3, c4, c5, c6, c7, c8}));
//    }
}