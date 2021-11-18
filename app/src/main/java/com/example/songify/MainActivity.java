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

import com.example.songify.retrofit.CancionesService;
import com.example.songify.roomdb.Cancion;
import com.example.songify.roomdb.CancionDatabase;
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
    List<Cancion> listaCanciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav_view);

        BottomNavigationView navView = findViewById(R.id.bottomNavView);
        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.listaCanciones, R.id.listaFavoritos, R.id.listaExitos).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

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
            //Cuando se implemente liveData
//            case R.id.menu_aToz:
//                //ordena de A a Z
//                Toast.makeText(this, "Ordenado de A->Z", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menu_zToa:
//                //ordena de Z a A
//                Toast.makeText(this, "Ordenado de Z->A", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menu_time:
//                //ordena por duracion
//                Toast.makeText(this, "Ordenado por Tiempo", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.menu_artistaToz:
//                //ordena por Artistas
//                Toast.makeText(this, "Ordenado por Artistas", Toast.LENGTH_SHORT).show();
//                return true;
            case R.id.menu_loadData:
                //carga todos los datos
                CancionDatabase cancionDatabase = CancionDatabase.getInstance(this);
                fillLista();
                return true;
            case R.id.menu_deleteData:
                //borra todos los datos
                cancionDatabase = CancionDatabase.getInstance(this);
                if (cancionDatabase.getDao().getAllCanciones() != null) {
                    cancionDatabase.getDao().deleteAll();
                } else {
                    Toast.makeText(this, "No hay datos en la base de datos", Toast.LENGTH_SHORT).show();
                }
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
                CancionDatabase cancionDatabase = CancionDatabase.getInstance(MainActivity.this);
                Cancion c;
                for (int i = 0; i < cancionList.size(); i++) {
                    c = cancionList.get(i);
                    cancionDatabase.getDao().insertCancion(c);
                }
            }

            @Override
            public void onFailure(Call<List<Cancion>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}