package com.example.songify.ui.favoritos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.songify.R;
import com.example.songify.ui.RecyclerViewAdapter;
import com.example.songify.ReproductorActivity;
import com.example.songify.data.Cancion;
import com.example.songify.viewmodel.CancionViewModel;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFavoritos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFavoritos extends Fragment {

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
    LiveData<List<Cancion>> listaFavoritos;
    private CancionViewModel mCancionViewModel;


    public FragmentFavoritos() {
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
    public static FragmentFavoritos newInstance(String param1, String param2) {
        FragmentFavoritos fragment = new FragmentFavoritos();
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

        mCancionViewModel.getAllFavoritos().observe(getViewLifecycleOwner(), cancions -> {
            mAdapter.swap(listaFavoritos);
            mAdapter.notifyDataSetChanged();
        });
        return vista;
    }

    //Se inicia la recyclerview
    private void initRecyclerViewCanciones(View vista) {

        //Se vincula el recyclerView con su layout correspondiente
        recyclerFavoritos = vista.findViewById(R.id.rv_Favoritos);
        recyclerFavoritos.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(vista.getContext());
        recyclerFavoritos.setLayoutManager(layoutManager);

        mCancionViewModel = new ViewModelProvider(this).get(CancionViewModel.class);
        mAdapter = new RecyclerViewAdapter(new MutableLiveData<List<Cancion>>(), vista.getContext());

        listaFavoritos = mCancionViewModel.getAllFavoritos();
        mAdapter.swap(listaFavoritos);

        //Se envia la cancion seleccionada al reproductor
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Se almacena en estas variables los datos de la cancion seleccionada por el usuario
                final String id = listaFavoritos.getValue().get(recyclerFavoritos.getChildAdapterPosition(view)).getId();
                final String titulo = listaFavoritos.getValue().get(recyclerFavoritos.getChildAdapterPosition(view)).getTitle();
                final String artista = listaFavoritos.getValue().get(recyclerFavoritos.getChildAdapterPosition(view)).getArtist();
                final String duracion = listaFavoritos.getValue().get(recyclerFavoritos.getChildAdapterPosition(view)).getDuration();
                final String picture = listaFavoritos.getValue().get(recyclerFavoritos.getChildAdapterPosition(view)).getPicture();
                final String preview = listaFavoritos.getValue().get(recyclerFavoritos.getChildAdapterPosition(view)).getPreview();

                //Se obtiene el contexto del main activity
                Intent intent = new Intent(view.getContext(),
                        ReproductorActivity.class);

                //Pasa el valor del id de la cancion seleccionada por el usuario
                intent.putExtra("ID", id);
                intent.putExtra("TITLE", titulo);
                intent.putExtra("ARTIST", artista);
                intent.putExtra("DURATION", duracion);
                intent.putExtra("PICTURE", picture);
                intent.putExtra("PREVIEW", preview);
                //Invoca la nueva activity
                startActivity(intent);
            }
        });
        recyclerFavoritos.setAdapter(mAdapter);

    }

}