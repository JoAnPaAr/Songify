package com.example.songify;

import android.os.Bundle;
import android.os.Handler;
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

import com.example.songify.retrofit.CancionesService;
import com.example.songify.roomdb.Cancion;
import com.example.songify.roomdb.CancionDatabase;
import com.example.songify.viewmodel.CancionViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Object BottomNavigationView;
    private CancionViewModel mCancionViewModel;
    final Handler handler = new Handler();
    final int delay = 30000; //30s

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
        //Se emplea para comprobar si la cache tiene elementos. En caso de no tenerlos, los busca
        handler.postDelayed(new Runnable() {
            public void run() {
                mCancionViewModel.init(); // Do your work here
                handler.postDelayed(this, delay);
            }
        }, delay);
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
                // Collections.sort(mCancionViewModel, Cancion.CancionAZComparator);
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
        mCancionViewModel.init();
    }
}