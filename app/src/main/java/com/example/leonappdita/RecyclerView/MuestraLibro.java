package com.example.leonappdita.RecyclerView;

public class MuestraLibro {
    private String imagen;
    private int id;
    private String link;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public MuestraLibro(int id, String imagen, String link){
        this.id = id;
        this.imagen = imagen;
        this.link = link;
    }

    public MuestraLibro(){

    }

}