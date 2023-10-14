package com.example.iglesia.Modelo.CatParentesco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.iglesia.Modelo.IglesiaDB;

import java.util.ArrayList;
import java.util.List;

public class ModeloCatParentesco extends IglesiaDB {

    Context context;

    public ModeloCatParentesco(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public void agregarCatParentesco(Integer id, String titulo, String descripcion){
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null){
            ContentValues valores = new ContentValues();
            valores.put("ID", id);
            valores.put("TITULO", titulo);
            valores.put("DESCRIPCION", descripcion);
            bd.insert("CATPARENTESCO", null, valores);
            bd.close();
        }
    }

    public List<ClaseCatParentesco> mostrarCatParentescos(){
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CATPARENTESCO", null);
        List<ClaseCatParentesco> catParentescos = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                catParentescos.add(new ClaseCatParentesco(Integer.valueOf(cursor.getString(0)),cursor.getString(1),cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return catParentescos;
    }

    public void buscarCatParentescos(ClaseCatParentesco miembro, Integer id){
        SQLiteDatabase bd = getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CATPARENTESCO WHERE ID='"+id+"' ", null);
        if (cursor.moveToFirst()){
            do {
                miembro.setTitulo(cursor.getString(1));
                miembro.setDescripcion(cursor.getString(2));
            } while (cursor.moveToNext());
        }
    }

    public void editarCatParentesco(Integer id, String titulo, String descripcion) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("TITULO", titulo);
            valores.put("DESCRIPCION", descripcion);
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.update("CATPARENTESCO", valores, whereClause, whereArgs);
            bd.close();
        }
    }

    public void eliminarCatParentesco(Integer id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            String whereClause = "ID = ?";
            String[] whereArgs = {String.valueOf(id)};
            bd.delete("CATPARENTESCO", whereClause, whereArgs);
            bd.close();
        }
    }



}
