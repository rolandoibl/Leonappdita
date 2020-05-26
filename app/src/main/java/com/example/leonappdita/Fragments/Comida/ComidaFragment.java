package com.example.leonappdita.Fragments.Comida;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.leonappdita.R;
import com.example.leonappdita.RecyclerView.AdaptadorMuestraProducto;
import com.example.leonappdita.RecyclerView.MuestraProducto;


//Tiene que heredar de Fragment
public class ComidaFragment extends Fragment {

    Button foodBtnDesayuno;
    Button foodBtnComida;
    Button foodBtnDulces;
    Button foodBtnPostres;
    Fragment fragmentSeleccionado;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Crear View con el layout
        View v = inflater.inflate(R.layout.fragment_comida,container,false);
        foodBtnDesayuno = v.findViewById(R.id.foodBtnDesayuno);
        foodBtnComida = v.findViewById(R.id.foodBtnComida);
        foodBtnDulces = v.findViewById(R.id.foodBtnDulces);
        foodBtnPostres = v.findViewById(R.id.foodBtnPostres);


        foodBtnDesayuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSeleccionado = new DesayunoFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();
            }
        });
        foodBtnComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Por el COVID-19, no hay comida",Toast.LENGTH_SHORT).show();
            }
        });
        foodBtnDulces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"No hay vendedores de dulces por el momento",Toast.LENGTH_SHORT).show();
            }
        });
        foodBtnPostres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Hay desabasto de postres :(",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}