package com.example.iglesia.Modelo.Cargo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.iglesia.Modelo.Cargo.ClaseCargo;
import com.example.iglesia.Modelo.IglesiaDB;

import java.util.ArrayList;
import java.util.List;

public class ModeloCargo extends IglesiaDB {

    Context context;

    public ModeloCargo(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public void agregarCargo(Integer id, String titulo, String descripcion){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null){
            ContentValues valores = new ContentValues();
            valores.put("ID", id);
            valores.put("TITULO", titulo);
            valores.put("DESCRIPCION", descripcion);
            bd.insert("CARGO", null, valores);
            bd.close();
        }
    }

    public List<ClaseCargo> mostrarCargos() {
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CARGO", null);
        List<ClaseCargo> cargos = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                cargos.add(new ClaseCargo(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return cargos;
    }

        public void buscarCargo(ClaseCargo miembro, Integer id){
            SQLiteDatabase bd = getWritableDatabase();
            Cursor cursor = bd.rawQuery("SELECT * FROM CARGO WHERE ID='"+id+"' ", null);
            if (cursor.moveToFirst()){
                do {
                    miembro.setTitulo(cursor.getString(1));
                    miembro.setDescripcion(cursor.getString(2));
                } while (cursor.moveToNext());
            }
        }

    public void editarCargo(Integer id, String titulo, String descripcion) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("TITULO", titulo);
            valores.put("DESCRIPCION", descripcion);
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.update("CARGO", valores, whereClause, whereArgs);
            bd.close();
        }
    }

    public void eliminarCargo(Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.delete("CARGO", whereClause, whereArgs);
            bd.close();
        }
    }



}
