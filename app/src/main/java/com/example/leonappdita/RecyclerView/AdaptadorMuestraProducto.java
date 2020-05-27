package com.example.leonappdita.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.leonappdita.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdaptadorMuestraProducto extends FirestoreRecyclerAdapter<MuestraProducto,AdaptadorMuestraProducto.MuestraProductoViewHolder> {

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
    public AdaptadorMuestraProducto(@NonNull FirestoreRecyclerOptions<MuestraProducto> options, Context context) {
        super(options);
        activityt = (Activity) context;
    }

    @NonNull
    @Override
    public MuestraProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_desayuno,parent,false);
        return new MuestraProductoViewHolder(vista);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MuestraProductoViewHolder holder, int position, @NonNull final MuestraProducto producto) {
        holder.txtvCVTitulo.setText(producto.getTitulo());
        Glide.with(activityt).load(producto.getImagen()).into(holder.imgbtnCVProducto);
        holder.txtvCVProveedor.setText(producto.getProveedor());
        holder.txtvCVContacto.setText("Contacto: "+producto.getContacto());
        holder.txtvCVDescripcion.setText(producto.getDescripcion());
        holder.txtvCVPrecio.setText(producto.getPrecio());
        holder.imgbtnCVProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"Estamos en la coleccion de",Toast.LENGTH_SHORT).show();

            }
        });
    }


    public static class MuestraProductoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtvCVTitulo;
        private ImageView imgbtnCVProducto;
        private TextView txtvCVProveedor;
        private TextView txtvCVContacto;
        private TextView txtvCVDescripcion;
        private TextView txtvCVPrecio;

        public MuestraProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtvCVTitulo = itemView.findViewById(R.id.desayunoTxtvTitulo);
            imgbtnCVProducto = itemView.findViewById(R.id.desayunoImgbtnProducto);
            txtvCVProveedor = itemView.findViewById(R.id.desayunoTxtvProveedor);
            txtvCVContacto = itemView.findViewById(R.id.desayunoTxtvContacto);
            txtvCVDescripcion = itemView.findViewById(R.id.desayunoTxtvDescripcion);
            txtvCVPrecio = itemView.findViewById(R.id.desayunoTxtvPrecio);
        }
    }
}
