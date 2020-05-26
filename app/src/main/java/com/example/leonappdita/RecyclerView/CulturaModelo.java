package com.example.leonappdita.RecyclerView;

public class CulturaModelo {

    private String titulo;
    private String imagen;
    private String link;

    public CulturaModelo(String titulo, String imagen, String link) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.link = link;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    public CulturaModelo() {
    }
}
