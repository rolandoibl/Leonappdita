package com.example.leonappdita.Fragments.Servicios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.leonappdita.Fragments.Servicios.MovEstFragment;
import com.example.leonappdita.Fragments.Servicios.COPADIFragment;
import com.example.leonappdita.Fragments.Servicios.GuiaEscFragment;
import com.example.leonappdita.Fragments.Servicios.ASDRIFragment;
import com.example.leonappdita.Fragments.Servicios.ServEscFragment;
import com.example.leonappdita.R;

//Tiene que heredar de Fragment
public class ServiciosFragment extends Fragment {

    Button btnServiciosMovEst;
    Button btnServiciosCOPADI;
    Button btnServiciosGuiaEsc;
    Button btnServiciosASDRI;
    Button btnServiciosServEsc;
    Fragment fragmentSeleccionado;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Crear View con el layout
        View v = inflater.inflate(R.layout.fragment_servicios,container,false);

        btnServiciosMovEst=v.findViewById(R.id.btnServiciosMovEst);
        btnServiciosCOPADI=v.findViewById(R.id.btnServiciosCOPADI);
        btnServiciosGuiaEsc=v.findViewById(R.id.btnServiciosGuiaEsc);
        btnServiciosASDRI=v.findViewById(R.id.btnServiciosASDRI);
        btnServiciosServEsc=v.findViewById(R.id.btnServiciosServEsc);
        btnServiciosMovEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSeleccionado = new MovEstFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();
            }
        });
        btnServiciosCOPADI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSeleccionado=new COPADIFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();

            }
        });
        btnServiciosGuiaEsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSeleccionado=new GuiaEscFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();

            }
        });
        btnServiciosASDRI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSeleccionado=new ASDRIFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();
            }
        });
        btnServiciosServEsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSeleccionado=new ServEscFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();
            }
        });
        return v;
    }
}
