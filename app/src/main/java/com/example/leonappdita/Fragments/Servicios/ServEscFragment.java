package com.example.leonappdita.Fragments.Servicios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.leonappdita.Fragments.Comida.DesayunoFragment;
import com.example.leonappdita.R;

public class ServEscFragment extends Fragment {
    Button btnServEscConstancias;
    Fragment fragmentSeleccionado;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_servesc,container,false);
        btnServEscConstancias = v.findViewById(R.id.btnServEscConstancias);
        btnServEscConstancias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentSeleccionado = new ConstanciasFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragContentMain,fragmentSeleccionado).commit();
            }
        });
        return v;
    }
}
