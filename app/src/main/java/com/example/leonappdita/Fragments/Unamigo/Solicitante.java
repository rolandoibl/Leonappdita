package com.example.leonappdita.Fragments.Unamigo;

public class Solicitante {
    public Solicitante(String nombre,String carrera,String numCuenta,String correo,String imagen) {
        this.nombre=nombre;
        this.carrera=carrera;
        this.numCuenta=numCuenta;
        this.correo=correo;
        this.imagen=imagen;
    }
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    private String carrera;
    private String numCuenta;
    private String correo;
    private String imagen;
}
