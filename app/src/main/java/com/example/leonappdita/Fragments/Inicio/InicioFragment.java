package com.example.leonappdita.Fragments.Inicio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.leonappdita.MainActivity;
import com.example.leonappdita.R;
import com.example.leonappdita.RecyclerView.AdaptadorMuestraLibro;
import com.example.leonappdita.RecyclerView.MuestraLibro;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Objects;

//Tiene que heredar de Fragment
public class InicioFragment extends Fragment {

    ImageButton inicioImgBtnBanner1;
    ImageButton inicioImgBtnBanner2;
    RecyclerView rcvFragInicioLibros;
    FirebaseFirestore mFirestore;
    //Eliminado al usar Firestone para RecyclerView
    //ArrayList<MuestraLibro> libros;
    FirestoreRecyclerOptions<MuestraLibro> firestoreRecyclerOptions;
    AdaptadorMuestraLibro adaptador;
    String imagen;
    String[] link = new String[2];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Crear View con el layout
        View v = inflater.inflate(R.layout.fragment_inicio,container,false);
        rcvFragInicioLibros = v.findViewById(R.id.rcvFragInicioLibros);
        inicioImgBtnBanner1 = v.findViewById(R.id.inicioImgbtnBanner1);
        inicioImgBtnBanner2 = v.findViewById(R.id.inicioImgbtnBanner2);
        rcvFragInicioLibros.setFocusable(false);
        //Como quieres que se comporte el recycler, pasar contexto
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcvFragInicioLibros.setLayoutManager(llm);
        //Inicializar firestore
        mFirestore = FirebaseFirestore.getInstance();
        //Crear método para consultar los libros de Firebase
        ConsultarLibros();
        //Crear método para consultar banners de Firebase
        ConsultarBanners();
        //InicializarLibros();
        InicializarAdaptador();
        inicioImgBtnBanner1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(link[0]);
                //Se crea un intent implicito para visualizar los links en un navegador
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //Se inicia la actividad del navegador
                startActivity(intent);
            }
        });

        inicioImgBtnBanner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(link[1]);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
        return v;
    }

    //Método que inicializa los banners de inicio
    private void ConsultarBanners() {
        try {
            mFirestore.collection("Inicio/Banners/banners").document("01").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    imagen = documentSnapshot.getString("imagen");
                    link[0] = documentSnapshot.getString("link");
                    try {
                        Glide.with(getActivity()).load(imagen).into(inicioImgBtnBanner1);
                    }catch (Exception e){

                    }
                }
            });

            mFirestore.collection("Inicio/Banners/banners").document("02").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    imagen = documentSnapshot.getString("imagen");
                    link[1] = documentSnapshot.getString("link");
                    try {
                        Glide.with(getActivity()).load(imagen).into(inicioImgBtnBanner2);
                    }catch (Exception e){
                        
                    }
                }
            });
        }
        catch (Exception e){

        }
    }

    //Método que consulta los libros de la base
    private void ConsultarLibros(){
        //Poner nombre de la colección
        Query consulta = mFirestore.collection("Inicio/Libros/libros");
        //Poner cual es el pollo, la consulta y el pollo otra vez
        firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MuestraLibro>().setQuery(consulta,MuestraLibro.class).build();
    }

    private void InicializarAdaptador() {
        //AdaptadorMuestraLibro adaptador = new AdaptadorMuestraLibro(libros);
        //Cambiar si se va a usar Firebase
        adaptador = new AdaptadorMuestraLibro(firestoreRecyclerOptions,getActivity());
        //El adaptador constantemente busca si hay un cambio en la base de datos
        adaptador.notifyDataSetChanged();
        rcvFragInicioLibros.setAdapter(adaptador);
    }

    //Métodos para iniciar búsqueda y terminar búsqueda de libros al cierre del fragment
    @Override
    public void onStart() {
        super.onStart();
        adaptador.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }

}
