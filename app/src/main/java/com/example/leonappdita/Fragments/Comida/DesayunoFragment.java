package com.example.leonappdita.Fragments.Comida;

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
import com.example.leonappdita.RecyclerView.AdaptadorMuestraProducto;
import com.example.leonappdita.RecyclerView.MuestraProducto;
import com.google.firebase.storage.FirebaseStorage;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.ArrayList;

public class DesayunoFragment extends Fragment {

    RecyclerView rcvfragDesayunoProducto;
    FirebaseFirestore mFirestore;
    FirestoreRecyclerOptions<MuestraProducto> firestoreRecyclerOptions;
    AdaptadorMuestraProducto adaptador;
    String imagen;
    String titulo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_desayuno,container,false);
        rcvfragDesayunoProducto = v.findViewById(R.id.rcvFragDesayunoProducto);
        LinearLayoutManager lle = new LinearLayoutManager(getActivity());
        lle.setOrientation(LinearLayoutManager.VERTICAL);
        rcvfragDesayunoProducto.setLayoutManager(lle);

        mFirestore = FirebaseFirestore.getInstance();
        ConsultarProductos();
        InicializarAdaptador();

        return v;
    }

    private void ConsultarProductos() {
        //Nombre de la coleccion
        Query consulta = mFirestore.collection("Comida/Productos/Desayuno");
        //Poner cual es el pollo, la consulta y el pollo otra vez
        firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MuestraProducto>().setQuery(consulta,MuestraProducto.class).build();
    }

    private void InicializarAdaptador() {
        adaptador = new AdaptadorMuestraProducto(firestoreRecyclerOptions,getContext());
        adaptador.notifyDataSetChanged();
        rcvfragDesayunoProducto.setAdapter(adaptador);
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

