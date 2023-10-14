package com.example.iglesia.Modelo.Ofrenda;

public class ClaseOfrenda {

    private int id;
    private double monto;

    private String fechaOfrenda;

    private int miembro_id;
    private int actividad_id;

    public ClaseOfrenda(){

    }

    public ClaseOfrenda(int id, double monto, String fechaPrestamo, int miembro_id, int categoria_id){
        this.id = id;
        this.monto = monto;
        this.fechaOfrenda = fechaOfrenda;
        this.miembro_id = miembro_id;
        this.actividad_id = actividad_id;
    }
    private ClaseOfrenda(Builder builder) {
        this.id = builder.id;
        this.monto = builder.monto;
        this.fechaOfrenda = builder.fechaOfrenda;
        this.miembro_id = builder.miembro_id;
        this.actividad_id = builder.actividad_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaOfrenda() {
        return fechaOfrenda;
    }

    public void setFechaOfrenda(String fechaOfrenda) {
        this.fechaOfrenda = fechaOfrenda;
    }


    public int getMiembro_id() {
        return miembro_id;
    }

    public void setMiembro_id(int miembro_id) {
        this.miembro_id = miembro_id;
    }

    public int getActividad_id() {
        return actividad_id;
    }

    public void setActividad_id(int actividad_id) {
        this.actividad_id = actividad_id;
    }

    // Clase Builder para construir objetos ClasePrestamo paso a paso
    public static class Builder {
        private int id;
        private double monto;
        private String fechaOfrenda;
        private int miembro_id;
        private int actividad_id;

        public Builder() {
            // Valores predeterminados o iniciales opcionales
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setMonto(double monto) {
            this.monto = monto;
            return this;
        }

        public Builder setFechaOfrenda(String fechaOfrenda) {
            this.fechaOfrenda = fechaOfrenda;
            return this;
        }


        public Builder setMiembro_id(int miembro_id) {
            this.miembro_id = miembro_id;
            return this;
        }

        public Builder setActividad_id(int actividad_id) {
            this.actividad_id = actividad_id;
            return this;
        }

        // Método para construir el objeto ClasePrestamo
        public ClaseOfrenda build() {
            return new ClaseOfrenda(this);
        }
    }
}
