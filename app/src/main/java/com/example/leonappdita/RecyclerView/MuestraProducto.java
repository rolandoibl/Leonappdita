package com.example.leonappdita.RecyclerView;

public class MuestraProducto {

    private String titulo;
    private String imagen;
    private String proveedor;
    private String contacto;
    private String descripcion;
    private String precio;

    public MuestraProducto(String titulo, String imagen, String proveedor, String contacto, String descripcion,String precio) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.proveedor = proveedor;
        this.contacto = contacto;
        this.descripcion = descripcion;
        this.precio = precio;
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

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    //Debe existir un constructor vacío para que la API no entre en conflicto
    public MuestraProducto(){

    }
}

