package com.example.leonappdita.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.leonappdita.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.List;

public class AdaptadorCultura extends FirestoreRecyclerAdapter<CulturaModelo,AdaptadorCultura.ViewHolder> {

    Activity activityt;
    public interface IColecProductos{
        //public void ClickProduccion(String titulo);
    }
    //AdaptadorCultura.IColecProductos activity;
    IColecProductos activity;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdaptadorCultura(@NonNull FirestoreRecyclerOptions<CulturaModelo> options, Context context) {
        super(options);
        activityt = (Activity) context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull CulturaModelo bannerCultura) {
        holder.titulo.setText(bannerCultura.getTitulo());
        Glide.with(activityt).load(bannerCultura.getImagen()).into(holder.fotoCultura);
        holder.fotoCultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Se abre la información",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        ImageView fotoCultura;
        private TextView link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=(TextView)itemView.findViewById(R.id.txtViTituloCultura);
            fotoCultura=(ImageView)itemView.findViewById(R.id.imgVCultura);
        }

    }

    /*
    public List<CulturaModelo> culturaLista;

    public AdaptadorCultura(List<CulturaModelo> culturaLista) {
        this.culturaLista = culturaLista;
    }  */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_cultura,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    //Comentado por el uso de Firebase

    /*
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titulo.setText(culturaLista.get(position).getTitulo());
        holder.fotoCultura.setImageResource(culturaLista.get(position).getFotoCultura());

    }  */

    /*
    @Override
    public int getItemCount() {


        return culturaLista.size();
    } */


}
