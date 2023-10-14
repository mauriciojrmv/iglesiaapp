package com.example.iglesia.Modelo.Organizacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.iglesia.Modelo.IglesiaDB;

import java.util.ArrayList;
import java.util.List;

public class ModeloOrganizacion extends IglesiaDB {

    Context context;

    public ModeloOrganizacion(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void agregarOrganizacion(Integer id, String nombre, String fecha, Integer miembroId, Integer cargoId) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("ID", id);
            valores.put("NOMBRE", nombre);
            valores.put("FECHA", fecha);
            valores.put("MIEMBRO_ID", miembroId);
            valores.put("CARGO_ID", cargoId);
            bd.insert("ORGANIZACION", null, valores);
            bd.close();
        }
    }

    public List<ClaseOrganizacion> mostrarOrganizaciones() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM ORGANIZACION", null);
        List<ClaseOrganizacion> organizaciones = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ClaseOrganizacion.Builder organizacionBuilder = new ClaseOrganizacion.Builder()
                        .setId(Integer.valueOf(cursor.getString(0)))
                        .setNombre(String.valueOf(cursor.getString(1)))
                        .setFecha(cursor.getString(2))
                        .setMiembro_id(Integer.valueOf(cursor.getString(3)))
                        .setCargo_id(Integer.valueOf(cursor.getString(4)));

                organizaciones.add(organizacionBuilder.build());
            } while (cursor.moveToNext());
        }
        return organizaciones;
    }

    public void buscarOrganizacion(ClaseOrganizacion.Builder organizacionBuilder, Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM ORGANIZACION WHERE ID='" + id + "' ", null);
        if (cursor.moveToFirst()) {
            do {
                organizacionBuilder
                        .setNombre(String.valueOf(cursor.getString(1)))
                        .setFecha(cursor.getString(2))
                        .setMiembro_id(Integer.valueOf(cursor.getString(3)))
                        .setCargo_id(Integer.valueOf(cursor.getString(4)));
            } while (cursor.moveToNext());
        }
    }

    public void editarOrganizacion(Integer id, String nombre,  String fecha, Integer miembroId, Integer cargoId) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("NOMBRE", nombre);
            valores.put("FECHA", fecha);
            valores.put("MIEMBRO_ID", miembroId);
            valores.put("CARGO_ID", cargoId);
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.update("ORGANIZACION", valores, whereClause, whereArgs);
            bd.close();
        }
    }

    public void eliminarOrganizacion(Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.delete("ORGANIZACION", whereClause, whereArgs);
            bd.close();
        }
    }

}
