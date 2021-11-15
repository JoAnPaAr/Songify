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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Object BottomNavigationView;


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