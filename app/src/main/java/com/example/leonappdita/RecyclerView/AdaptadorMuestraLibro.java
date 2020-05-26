package com.example.leonappdita.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.leonappdita.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

public class AdaptadorMuestraLibro extends FirestoreRecyclerAdapter<MuestraLibro,AdaptadorMuestraLibro.MuestraLibroViewHolder> {

    Activity activityt;

    public interface IColecProductos{
        //public void ClickProduccion(String titulo);
    }
    IColecProductos activity;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdaptadorMuestraLibro(@NonNull FirestoreRecyclerOptions<MuestraLibro> options, Context context) {
        super(options);
        //Pivote para indicar la actividad que se ejecuta (porque necesitamos un contexto)
        //activity = (IColecProductos)context;
        activityt = (Activity) context;
    }

    //Comentado en cambio a firebase
    //ArrayList<MuestraLibro> libros;

    //Comentado en cambio a firebase
    /*
    public AdaptadorMuestraLibro(ArrayList<MuestraLibro> libros){
        this.libros = libros;
    }*/

    @NonNull
    @Override
    public MuestraLibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_libro,parent,false);
        return new MuestraLibroViewHolder(vista);
    }

    @Override
    protected void onBindViewHolder(@NonNull MuestraLibroViewHolder holder, int position, @NonNull MuestraLibro libro) {
        //Para imágenes de firestore
        //Glide.with((Activity)activity).load(libro.getImagen()).into(holder.imgBtnCVLibro);
        Glide.with(activityt).load(libro.getImagen()).into(holder.imgBtnCVLibro);
        holder.imgBtnCVLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Descargando archivo...",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Comentado en cambio a firebase
    /*
    @Override
    public void onBindViewHolder(@NonNull MuestraLibroViewHolder holder, int position) {
        final MuestraLibro libro = libros.get(position);
        //holder.imgBtnCVLibro.setImageResource(libro.getImagen());
        holder.imgBtnCVLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Descargando archivo...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }*/

    public static class MuestraLibroViewHolder extends RecyclerView.ViewHolder{

        private ImageButton imgBtnCVLibro;

        public MuestraLibroViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBtnCVLibro = itemView.findViewById(R.id.imgBtnCVLibro);
        }
    }

}

