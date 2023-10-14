package com.example.iglesia.Modelo.Organizacion;

public class ClaseOrganizacion {

    private int id;
    private String nombre;

    private String fecha;

    private int miembro_id;
    private int cargo_id;

    public ClaseOrganizacion(){

    }

    public ClaseOrganizacion(int id, String nombre, String fecha, int miembro_id, int cargo_id){
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.miembro_id = miembro_id;
        this.cargo_id = cargo_id;
    }
    private ClaseOrganizacion(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.fecha = builder.fecha;
        this.miembro_id = builder.miembro_id;
        this.cargo_id = builder.cargo_id;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMiembro_id() {
        return miembro_id;
    }

    public void setMiembro_id(int miembro_id) {
        this.miembro_id = miembro_id;
    }

    public int getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(int cargo_id) {
        this.cargo_id = cargo_id;
    }

    // Clase Builder para construir objetos ClasePrestamo paso a paso
    public static class Builder {
        private int id;
        private String nombre;
        private String fecha;
        private int miembro_id;
        private int cargo_id;

        public Builder() {
            // Valores predeterminados o iniciales opcionales
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setFecha(String fecha) {
            this.fecha = fecha;
            return this;
        }


        public Builder setMiembro_id(int miembro_id) {
            this.miembro_id = miembro_id;
            return this;
        }

        public Builder setCargo_id(int cargo_id) {
            this.cargo_id = cargo_id;
            return this;
        }

        // Método para construir el objeto ClasePrestamo
        public ClaseOrganizacion build() {
            return new ClaseOrganizacion(this);
        }
    }
}
