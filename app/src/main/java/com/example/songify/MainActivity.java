package com.example.songify;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import com.example.songify.retrofit.CancionesService;
import com.example.songify.roomdb.Cancion;
import com.example.songify.roomdb.CancionDAO;
import com.example.songify.roomdb.CancionDatabase;
import com.example.songify.viewmodel.CancionViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Object BottomNavigationView;
    List<Cancion> listaCanciones;
    RecyclerViewAdapter myAdapter;
    private CancionDAO mCancionDao;
    private CancionViewModel mCancionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav_view);

        BottomNavigationView navView = findViewById(R.id.bottomNavView);
        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.listaCanciones, R.id.listaFavoritos, R.id.listaExitos).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        mCancionViewModel = new ViewModelProvider(this).get(CancionViewModel.class);
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
        CancionDatabase cancionDatabase = CancionDatabase.getInstance(this);
        switch (item.getItemId()) {
            //Cuando se implemente liveData
            case R.id.menu_aToz:
                //ordena de A a Z
                //Collections.sort(mCancionDao, Cancion.CancionAZComparator);
                Toast.makeText(this, "Ordenado de A->Z", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_zToa:
                //ordena de Z a A
                //Collections.sort(mCancionDao, Cancion.CancionZAComparator);
                Toast.makeText(this, "Ordenado de Z->A", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_time:
                //ordena por duracion
                //Collections.sort(mCancionDao, Cancion.CancionDurationComparator);
                Toast.makeText(this, "Ordenado por Tiempo", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_artistaToz:
                //ordena por Artistas
                //Collections.sort(mCancionDao,Cancion.CancionAZArtistComparator);
                Toast.makeText(this, "Ordenado por Artistas", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_loadData:
                //carga todos los datos
                fillLista();
                return true;
            case R.id.menu_deleteData:
                //borra todos los datos
                mCancionViewModel.borrar();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fillLista() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CancionesService service = retrofit.create(CancionesService.class);

        service.getAllCanciones().enqueue(new Callback<List<Cancion>>() {
            @Override
            public void onResponse(Call<List<Cancion>> call, Response<List<Cancion>> response) {
                List<Cancion> cancionList = response.body();
                listaCanciones = cancionList;
                Cancion c;
                for (int i = 0; i < cancionList.size(); i++) {
                    c = cancionList.get(i);
                    //Invocar al insert en viewmodel
                    mCancionViewModel.insert(c);
                }
            }

            @Override
            public void onFailure(Call<List<Cancion>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}