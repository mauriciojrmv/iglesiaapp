package com.example.iglesia.Modelo.Ofrenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.iglesia.Modelo.IglesiaDB;

import java.util.ArrayList;
import java.util.List;

public class ModeloOfrenda extends IglesiaDB {

    Context context;

    public ModeloOfrenda(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void agregarOfrenda(Integer id, double monto, String fechaPrestamo, Integer miembroId, Integer actividadId) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("ID", id);
            valores.put("MONTO", monto);
            valores.put("FECHAOFRENDA", fechaPrestamo);
            valores.put("MIEMBRO_ID", miembroId);
            valores.put("OFRENDA_ID", actividadId);
            bd.insert("OFRENDA", null, valores);
            bd.close();
        }
    }

    public List<ClaseOfrenda> mostrarOfrendas() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM OFRENDA", null);
        List<ClaseOfrenda> ofrendas = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                ClaseOfrenda.Builder ofrendaBuilder = new ClaseOfrenda.Builder()
                        .setId(Integer.valueOf(cursor.getString(0)))
                        .setMonto(Double.valueOf(cursor.getString(1)))
                        .setFechaOfrenda(cursor.getString(2))
                        .setMiembro_id(Integer.valueOf(cursor.getString(3)))
                        .setActividad_id(Integer.valueOf(cursor.getString(4)));

                ofrendas.add(ofrendaBuilder.build());
            } while (cursor.moveToNext());
        }
        return ofrendas;
    }

    public void buscarOfrenda(ClaseOfrenda.Builder ofrendaBuilder, Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM OFRENDA WHERE ID='" + id + "' ", null);
        if (cursor.moveToFirst()) {
            do {
                ofrendaBuilder
                        .setMonto(Double.valueOf(cursor.getString(1)))
                        .setFechaOfrenda(cursor.getString(2))
                        .setMiembro_id(Integer.valueOf(cursor.getString(3)))
                        .setActividad_id(Integer.valueOf(cursor.getString(4)));
            } while (cursor.moveToNext());
        }
    }

    public void editarOfrenda(Integer id, double monto,  String fechaOfrenda, Integer miembroId, Integer actividadId) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("MONTO", monto);
            valores.put("FECHAOFRENDA", fechaOfrenda);
            valores.put("MIEMBRO_ID", miembroId);
            valores.put("ACTIVIDAD_ID", actividadId);
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.update("OFRENDA", valores, whereClause, whereArgs);
            bd.close();
        }
    }

    public void eliminarOfrenda(Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.delete("OFRENDA", whereClause, whereArgs);
            bd.close();
        }
    }

}
