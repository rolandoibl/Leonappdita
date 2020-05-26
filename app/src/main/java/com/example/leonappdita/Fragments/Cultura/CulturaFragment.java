package com.example.leonappdita.Fragments.Cultura;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leonappdita.R;
import com.example.leonappdita.RecyclerView.AdaptadorCultura;
import com.example.leonappdita.RecyclerView.AdaptadorMuestraLibro;
import com.example.leonappdita.RecyclerView.CulturaModelo;
import com.example.leonappdita.RecyclerView.MuestraLibro;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

//Tiene que heredar de Fragment
public class CulturaFragment extends Fragment {

   /*private RecyclerView recyclerViewCultura;
    private AdaptadorCultura adaptadorCultura;*/  // Comentado por uso de Firestore

    FirebaseFirestore mFirestore;
    FirestoreRecyclerOptions<CulturaModelo> firestoreRecyclerOptions;
    private AdaptadorCultura adaptadorCultura;
    private RecyclerView recyclerViewCultura;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Crear View con el layout
        View v = inflater.inflate(R.layout.fragment_cultura,container,false);
        recyclerViewCultura=(RecyclerView)v.findViewById(R.id.rcvFragCultura);
        //Como quiero que se comporte el Recycle
        recyclerViewCultura.setLayoutManager(new LinearLayoutManager(v.getContext()));
        //Inicializar Firestore
        mFirestore = FirebaseFirestore.getInstance();
        //Método para consultar Banners en firebase
        ConsultarBanners();
        //Inicializar adaptador
        InicializarAdaptador();

        /*adaptadorCultura=new AdaptadorCultura(obtenerCardCultura());
        recyclerViewCultura.setAdapter(adaptadorCultura);*/ // Comentado por firebase

        return v;
    }

    private void InicializarAdaptador() {
        //Cambiar si se va a usar Firebase
        adaptadorCultura = new AdaptadorCultura(firestoreRecyclerOptions,getActivity());
        //El adaptador constantemente busca si hay un cambio en la base de datos
        adaptadorCultura.notifyDataSetChanged();
        recyclerViewCultura.setAdapter(adaptadorCultura);
    }

    private void ConsultarBanners() {
        //Poner nombre de la colección
        Query consulta = mFirestore.collection("Cultura/Banners/banners");
        //Poner cual es el pollo, la consulta y el pollo otra vez
        firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<CulturaModelo>().setQuery(consulta,CulturaModelo.class).build();
    }

    //Métodos para iniciar búsqueda y terminar búsqueda de libros al cierre del fragment
    @Override
    public void onStart() {
        super.onStart();
        adaptadorCultura.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adaptadorCultura.stopListening();
    }

 /*   public List<CulturaModelo> obtenerCardCultura(){

        List<CulturaModelo> cultura=new ArrayList<>();
        cultura.add(new CulturaModelo("Becas",R.drawable.becas));
        cultura.add(new CulturaModelo("Conoce los centros de recursos de aprendizaje",R.drawable.centroderecursosaprendizaje));
        cultura.add(new CulturaModelo("Ven a la conferencia sobre la igualdad",R.drawable.conferenciasmujer));
        cultura.add(new CulturaModelo("¿Qué hará la FI respecto al COVID",R.drawable.covidfi));
        cultura.add(new CulturaModelo("Conoce la Guía Universitaria de Protección",R.drawable.guiaunivesitariaproteccion));
        cultura.add(new CulturaModelo("¿Y tú como te vas a titular?",R.drawable.opcionestitulacion));
        cultura.add(new CulturaModelo("Plan de desarrollo de la DCB",R.drawable.plandedesarrollodeladcb));
        cultura.add(new CulturaModelo("Aprende por ti mismo",R.drawable.sitioautoaprendizajequimica));

        return cultura;

    }*/






}

