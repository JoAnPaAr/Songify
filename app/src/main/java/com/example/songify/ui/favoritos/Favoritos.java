package com.example.songify.ui.favoritos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songify.R;
import com.example.songify.RecyclerViewAdapter;
import com.example.songify.ReproductorActivity;
import com.example.songify.roomdb.Cancion;
import com.example.songify.roomdb.CancionDatabase;

import java.util.ArrayList;
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
    private RecyclerViewAdapter mAdapter;
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

        //En este metodo se invoca al metodo que vincula la RecyclerView con su layout
        initRecyclerViewCanciones(vista);

        return vista;
    }


    //Se inicia la recyclerview
    private void initRecyclerViewCanciones(View vista) {

        //Se vincula el recyclerView con su layout correspondiente
        recyclerFavoritos = vista.findViewById(R.id.rv_Favoritos);
        recyclerFavoritos.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(vista.getContext());
        recyclerFavoritos.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(new ArrayList<>(), vista.getContext());

        CancionDatabase cancionDatabase = CancionDatabase.getInstance(vista.getContext());
        mAdapter.swap(cancionDatabase.getDao().getAllFavorites());

        //Se envia la cancion seleccionada al reproductor
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listaFavoritos = cancionDatabase.getDao().getAllFavorites();
                //Se almacena en estas variables los datos de la cancion seleccionada por el usuario
                final String id = listaFavoritos.get(recyclerFavoritos.getChildAdapterPosition(view)).getId();
                //Se obtiene el contexto del main activity
                Intent intent = new Intent(view.getContext(),
                        ReproductorActivity.class);

                //Pasa el valor del id de la cancion seleccionada por el usuario
                intent.putExtra("ID", id);

                //Invoca la nueva activity
                startActivity(intent);
            }
        });
        recyclerFavoritos.setAdapter(mAdapter);

    }

}