package com.example.leonappdita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.leonappdita.Fragments.Comida.ComidaFragment;
import com.example.leonappdita.Fragments.Cultura.CulturaFragment;
import com.example.leonappdita.Fragments.Inicio.InicioFragment;
import com.example.leonappdita.Fragments.Servicios.ServiciosFragment;
import com.example.leonappdita.Fragments.Unamigo.UnamigoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    //Importar design 29.0.0
    BottomNavigationView btmNavPrincipal;
    //Declarando fragment
    Fragment fragmentSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Asociando parte gráfica a lógica
        btmNavPrincipal = findViewById(R.id.btmNavPrincipal);
        //Inicializando fragment de inicio
        fragmentSeleccionado = new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();
        //Asignando listener al BottomNavView
        btmNavPrincipal.setOnNavigationItemSelectedListener(onNavMenuSelect);
    }

    //Definiendo acciones en BottomNavigation
    BottomNavigationView.OnNavigationItemSelectedListener onNavMenuSelect= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId())
            {
                case R.id.menu_nav_inicio:
                    fragmentSeleccionado = new InicioFragment();
                    break;
                case R.id.menu_nav_cultura:
                    fragmentSeleccionado = new CulturaFragment();
                    break;
                case R.id.menu_nav_servicios:
                    fragmentSeleccionado = new ServiciosFragment();
                    break;
                case R.id.menu_nav_comida:
                    fragmentSeleccionado = new ComidaFragment();
                    break;
                case R.id.menu_nav_unamigo:
                    fragmentSeleccionado = new UnamigoFragment();
                    break;
            }
            //Ya con el fragment seleccionado, reemplazar el actual
            getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();
            return true;
        }
    };


}
