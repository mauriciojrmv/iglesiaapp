package com.example.iglesia.Modelo.Miembro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.iglesia.Modelo.IglesiaDB;

import java.util.ArrayList;
import java.util.List;

public class ModeloMiembro extends IglesiaDB implements IModeloMiembro {

    Context context;

    public ModeloMiembro(@Nullable Context context){
        super(context);
        this.context = context;
    }

    @Override
    public void agregarMiembro(Integer id, String nombre,String apellido, Integer carnet, String celular){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null){
            ContentValues valores = new ContentValues();
            valores.put("ID", id);
            valores.put("NOMBRE", nombre);
            valores.put("APELLIDO", apellido);
            valores.put("CARNET", carnet);
            valores.put("CELULAR", celular);
            bd.insert("MIEMBRO", null, valores);
            bd.close();
        }
    }

    @Override
    public List<ClaseMiembro> mostrarMiembros(){
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM MIEMBRO", null);
        List<ClaseMiembro> miembros = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                miembros.add(new ClaseMiembro(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Integer.valueOf(cursor.getString(3)),cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        return miembros;
    }


    @Override
    public void buscarMiembro(ClaseMiembro miembro, Integer id){
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM MIEMBRO WHERE ID='"+id+"' ", null);
        if (cursor.moveToFirst()){
            do {
                miembro.setNombre(cursor.getString(1));
                miembro.setApellido(cursor.getString(2));
                miembro.setCarnet(Integer.parseInt(cursor.getString(3)));
                miembro.setTelefono(String.valueOf(Integer.valueOf(cursor.getString(4))));
            } while (cursor.moveToNext());
        }
    }

    @Override
    public void editarMiembro(Integer id, String nombre, String correo, Integer telefono, String direccion) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("NOMBRE", nombre);
            valores.put("CORREO", correo);
            valores.put("TELEFONO", telefono);
            valores.put("DIRECCION", direccion);
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.update("MIEMBRO", valores, whereClause, whereArgs);
            bd.close();
        }
    }

    @Override
    public void eliminarMiembro(Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.delete("MIEMBRO", whereClause, whereArgs);
            bd.close();
        }
    }



}
