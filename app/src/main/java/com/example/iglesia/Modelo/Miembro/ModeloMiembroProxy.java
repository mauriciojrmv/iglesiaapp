package com.example.iglesia.Modelo.Miembro;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.iglesia.Modelo.IglesiaDB;

import java.util.List;

public class ModeloMiembroProxy extends IglesiaDB implements IModeloMiembro{

    Context context;
    ModeloMiembro modeloMiembro;

    boolean agregadoCorrectamente;
    public ModeloMiembroProxy(@Nullable Context context) {
        super(context);
        this.context = context;
        modeloMiembro = new ModeloMiembro(context.getApplicationContext());
    }


    @Override
    public void agregarMiembro(Integer id, String nombre, String correo, Integer telefono, String direccion) {
        // Validaciones adicionales antes de llamar al método original

        if (id <= 0) {
            agregadoCorrectamente = false;
            Toast.makeText(context.getApplicationContext(), "Error: El ID del miembro debe ser mayor a cero.", Toast.LENGTH_SHORT).show();
            return; // Salir del método sin llamar al método original
        }

        if (nombre == null || nombre.isEmpty()) {
            agregadoCorrectamente = false;
            Toast.makeText(context.getApplicationContext(), "Error: El nombre del miembro no puede estar vacío.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (correo != null && !correo.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            agregadoCorrectamente = false;
            Toast.makeText(context.getApplicationContext(), "Error: El correo electrónico no es válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (telefono != null && (telefono.toString().length() > 8 || telefono <= 0)) {
            agregadoCorrectamente = false;
            Toast.makeText(context.getApplicationContext(), "Error: El número de teléfono no es válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Llamar al método original
        modeloMiembro.agregarMiembro(id, nombre, correo, telefono, direccion);
        agregadoCorrectamente = true;
    }

    @Override
    public List<ClaseMiembro> mostrarMiembros() {
        return modeloMiembro.mostrarMiembros();
    }

    @Override
    public void buscarMiembro(ClaseMiembro miembro, Integer id) {
        if (id <= 0) {
            Toast.makeText(context.getApplicationContext(), "Error: El ID del miembro debe ser mayor a cero.", Toast.LENGTH_SHORT).show();
            return; // Salir del método sin llamar al método original
        }
        modeloMiembro.buscarMiembro(miembro, id);
    }

    @Override
    public void editarMiembro(Integer id, String nombre, String apellido, Integer carnet, String telefono) {
        if (id <= 0) {
            agregadoCorrectamente = false;
            Toast.makeText(context.getApplicationContext(), "Error: El ID del miembro debe ser mayor a cero.", Toast.LENGTH_SHORT).show();
            return; // Salir del método sin llamar al método original
        }

        if (nombre == null || nombre.isEmpty()) {
            agregadoCorrectamente = false;
            Toast.makeText(context.getApplicationContext(), "Error: El nombre del miembro no puede estar vacío.", Toast.LENGTH_SHORT).show();;
            return; // Salir del método sin llamar al método original
        }

        if (apellido == null || apellido.isEmpty()) {
            agregadoCorrectamente = false;
            Toast.makeText(context.getApplicationContext(), "Error: El apellido del miembro no puede estar vacío.", Toast.LENGTH_SHORT).show();;
            return; // Salir del método sin llamar al método original
        }

        if (carnet == null) {
            agregadoCorrectamente = false;
            Toast.makeText(context.getApplicationContext(), "Error: El carnet no es válido.", Toast.LENGTH_SHORT).show();
            return; // Salir del método sin llamar al método original
        }

        if (telefono != null && (telefono.toString().length() >8)) {
            agregadoCorrectamente = false;
            Toast.makeText(context.getApplicationContext(), "Error: El número de teléfono no es válido.", Toast.LENGTH_SHORT).show();
            return; // Salir del método sin llamar al método original
        }

        // Llamar al método original
        modeloMiembro.editarMiembro(id, nombre, apellido, carnet, telefono);
        agregadoCorrectamente = true;
    }

    @Override
    public void eliminarMiembro(Integer id) {
        // Validaciones adicionales antes de llamar al método original
        if (id <= 0) {
            Toast.makeText(context.getApplicationContext(), "Error: El ID del miembro debe ser mayor a cero.", Toast.LENGTH_SHORT).show();
            return; // Salir del método sin llamar al método original
        }

        // Llamar al método original
        modeloMiembro.eliminarMiembro(id);
    }

    public boolean isAgregadoCorrectamente() {
        return agregadoCorrectamente;
    }
}
