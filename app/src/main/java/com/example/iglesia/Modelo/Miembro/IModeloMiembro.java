package com.example.iglesia.Modelo.Miembro;

import java.util.List;

public interface IModeloMiembro {

    void agregarMiembro(Integer id, String nombre, String apellido, Integer carnet, String celular);

    List<ClaseMiembro> mostrarMiembros();

    void buscarMiembro(ClaseMiembro Miembro, Integer id);

    void editarMiembro(Integer id, String nombre, String apellido, Integer carnet, String celular);

    void eliminarMiembro(Integer id);

}
