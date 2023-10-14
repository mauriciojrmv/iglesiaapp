package com.example.iglesia.Modelo.Miembro;

public class ClaseMiembro {

    private int id;
    private String nombre;
    private String apellido;
    private int carnet;
    private String telefono;


    public ClaseMiembro(){

    }

    public ClaseMiembro(int id, String nombre, String apellido, int carnet, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carnet = carnet;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
