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
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DulcesFragment extends Fragment {
    RecyclerView rcvfragDulcesProducto;
    FirebaseFirestore mFirestore;
    FirestoreRecyclerOptions<MuestraProducto> firestoreRecyclerOptions;
    AdaptadorMuestraProducto adaptador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dulces,container,false);
        rcvfragDulcesProducto = v.findViewById(R.id.rcvFragDulcesProducto);
        LinearLayoutManager lle = new LinearLayoutManager(getActivity());
        lle.setOrientation(LinearLayoutManager.VERTICAL);
        rcvfragDulcesProducto.setLayoutManager(lle);

        mFirestore = FirebaseFirestore.getInstance();
        ConsultarProductos();
        InicializarAdaptador();

        return v;
    }

    private void ConsultarProductos() {
        //Nombre de la coleccion
        Query consulta = mFirestore.collection("Comida/Productos/Dulces");
        //Poner cual es el pollo, la consulta y el pollo otra vez
        firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<MuestraProducto>().setQuery(consulta,MuestraProducto.class).build();
    }

    private void InicializarAdaptador() {
        adaptador = new AdaptadorMuestraProducto(firestoreRecyclerOptions,getContext());
        adaptador.notifyDataSetChanged();
        rcvfragDulcesProducto.setAdapter(adaptador);
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
