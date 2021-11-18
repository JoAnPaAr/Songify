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
import com.example.songify.retrofit.CancionesService;
import com.example.songify.roomdb.Cancion;
import com.example.songify.roomdb.CancionDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        //Este metodo llena la lista de canciones
        // fillListaCancion();
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

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CancionesService service = retrofit.create(CancionesService.class);

        service.getAllCanciones().enqueue(new Callback<List<Cancion>>() {
            @Override
            public void onResponse(Call<List<Cancion>> call, Response<List<Cancion>> response) {
                List<Cancion> cancionList = response.body();
                listaFavoritos=cancionList;
                CancionDatabase cancionDatabase = CancionDatabase.getInstance(vista.getContext());
//                Cancion c;
//                for(int i = 0; i<cancionList.size();i++){
//                    c=cancionList.get(i);
//                    cancionDatabase.getDao().insertCancion(c);
//                }
                mAdapter.swap(cancionDatabase.getDao().getAllFavorites());
            }

            @Override
            public void onFailure(Call<List<Cancion>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        //Se envia la cancion seleccionada al reproductor
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Se almacena en estas variables los datos de la cancion seleccionada por el usuario
                final String id = listaFavoritos.get(recyclerFavoritos.getChildAdapterPosition(view)).getId().toString();
                //Se obtiene el contexto del main activity
                Intent intent = new Intent(view.getContext(),
                        ReproductorActivity.class);

                //Pasa el valor del id de la cancion seleccionada por el usuario
                intent.putExtra("ID", id);

                //Pasa la lista de canciones al reproductor
                intent.putExtra("LIST", (Serializable) listaFavoritos);

                //Invoca la nueva activity
                startActivity(intent);
            }
        });
        recyclerFavoritos.setAdapter(mAdapter);
    }

}