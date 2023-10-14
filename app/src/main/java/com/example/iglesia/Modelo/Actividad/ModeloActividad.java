package com.example.iglesia.Modelo.Actividad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.iglesia.Modelo.IglesiaDB;

import java.util.ArrayList;
import java.util.List;

public class ModeloActividad extends IglesiaDB {

    Context context;

    public ModeloActividad(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public void agregarActividad(Integer id, String nombre, String fecha,String hora){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null){
            ContentValues valores =  new ContentValues();
            valores.put("ID", id);
            valores.put("NOMBRE", nombre);
            valores.put("FECHA", fecha);
            valores.put("HORA", hora);
            bd.insert("ACTIVIDAD", null, valores);
            bd.close();
        }
    }

    public List<ClaseActividad> mostrarActividades() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM ACTIVIDAD", null);
        List<ClaseActividad> actividades = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                actividades.add(new ClaseActividad(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        return actividades;
    }

    public void buscarActividad(ClaseActividad actividad, Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM ACTIVIDAD WHERE ID='" + id + "' ", null);
        if (cursor.moveToFirst()) {
            do {
                actividad.setNombre(cursor.getString(1));
                actividad.setFecha(cursor.getString(2));
                actividad.setHora(cursor.getString(3));
            } while (cursor.moveToNext());
        }
    }

    public void editarActividad(Integer id, String nombre, String fecha, String hora) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("NOMBRE", nombre);
            valores.put("FECHA", fecha);
            valores.put("HORA", hora);
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.update("ACTIVIDAD", valores, whereClause, whereArgs);
            bd.close();
        }
    }

    public void eliminarActividad(Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.delete("ACTIVIDAD", whereClause, whereArgs);
            bd.close();
        }
    }
}
