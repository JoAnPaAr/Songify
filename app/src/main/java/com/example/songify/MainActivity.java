package com.example.songify;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songify.model.Cancion;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Object BottomNavigationView;
    Menu menu;
    List<Cancion> listaCanciones = new ArrayList<Cancion>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav_view);

        fillListaCancion();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        recyclerView = findViewById(R.id.rv_listaCancion);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new RecyclerViewAdapter(listaCanciones,this);
        recyclerView.setAdapter(mAdapter);

    }

    //(String id, String title, String artist, String duration)
    private void fillListaCancion() {
        Cancion c0 = new Cancion("0","Cancion2","Blur","180","https://img.discogs.com/SIySlohaBvKNM722OvT7NGpZxUg=/fit-in/600x600/filters:strip_icc():format(jpeg):mode_rgb():quality(90)/discogs-images/R-385550-1266624470.jpeg.jpg");
        Cancion c1 = new Cancion("1","Giants","BeckyG","230","https://elrework.com/wp-content/uploads/2019/11/true-damage.jpg");
        Cancion c2 = new Cancion("2","Pretty Fly","Offspring","300","https://pics.filmaffinity.com/The_Offspring_Pretty_Fly_for_a_White_Guy_V_deo_musical-939917680-large.jpg");
        Cancion c3 = new Cancion("3","Let's Go","Stuck to the Sound","240","https://i1.sndcdn.com/artworks-000353762163-7bo5aj-t500x500.jpg");
        Cancion c4 = new Cancion("4","Taking Over","LeagueOfLegends","290","https://m.media-amazon.com/images/I/91ApZlV-GBL._SS500_.jpg");
        Cancion c5 = new Cancion("5","Star","Lil Cake","230","https://s.mxmcdn.net/images-storage/albums5/6/4/3/4/0/6/56604346_500_500.jpg");
        Cancion c6 = new Cancion("6","Onion","PinnochioP","90","https://i1.sndcdn.com/artworks-000576487046-fdjb9g-t500x500.jpg");
        Cancion c7 = new Cancion("7","Reloaded Installer #13","LHSchiptunes","217","https://i.ytimg.com/vi/CYql_6MRNPU/sddefault.jpg");
        Cancion c8 = new Cancion("8","Lost Woods","The Legend of Zelda","120","https://i.ytimg.com/vi/7RbdY_hcUAA/0.jpg");

        listaCanciones.addAll(Arrays.asList(new Cancion[] {c0,c1,c2,c3,c4,c5,c6,c7,c8}));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_aToz:
                //ordena de A a Z
                Toast.makeText(this, "Ordenado de A->Z", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_zToa:
                //ordena de Z a A
                Toast.makeText(this, "Ordenado de Z->A", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_time:
                //ordena por duracion
                Toast.makeText(this, "Ordenado por Tiempo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_artistaToz:
                //ordena por Artistas
                Toast.makeText(this, "Ordenado por Artistas", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}