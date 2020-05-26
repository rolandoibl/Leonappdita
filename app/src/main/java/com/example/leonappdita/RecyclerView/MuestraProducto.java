package com.example.leonappdita.RecyclerView;

public class MuestraProducto {
    public MuestraProducto(String titulo, String imagen) {
        this.titulo = titulo;
        this.imagen = imagen;
    }

    private String titulo;
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public MuestraProducto(){

    }
}

