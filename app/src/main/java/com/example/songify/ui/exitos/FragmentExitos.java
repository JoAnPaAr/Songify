package com.example.songify.ui.exitos;

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
import com.example.songify.RecyclerViewAdapter;
import com.example.songify.roomdb.Cancion;
import com.example.songify.viewmodel.CancionViewModel;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentExitos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentExitos extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerExitos;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    LiveData<List<Cancion>> listaExitos;
    private CancionViewModel mCancionViewModel;


    public FragmentExitos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaExitos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentExitos newInstance(String param1, String param2) {
        FragmentExitos fragment = new FragmentExitos();
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
        View vista = inflater.inflate(R.layout.fragment_lista_exitos, container, false);

        //En este metodo se invoca al metodo que vincula la RecyclerView con su layout
        initRecyclerViewCanciones(vista);

        mCancionViewModel.getAllExitos().observe(getViewLifecycleOwner(), cancions -> {
            mAdapter.swap(listaExitos);
            mAdapter.notifyDataSetChanged();
        });
        return vista;
    }

    //Se inicia la recyclerview
    private void initRecyclerViewCanciones(View vista) {

        //Se vincula el recyclerView con su layout correspondiente
        recyclerExitos = vista.findViewById(R.id.rv_Exitos);
        recyclerExitos.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(vista.getContext());
        recyclerExitos.setLayoutManager(layoutManager);

        mCancionViewModel = new ViewModelProvider(this).get(CancionViewModel.class);
        mAdapter = new RecyclerViewAdapter(new MutableLiveData<List<Cancion>>(), vista.getContext());

        listaExitos = mCancionViewModel.getAllExitos();
        mAdapter.swap(listaExitos);

        recyclerExitos.setAdapter(mAdapter);

    }
}