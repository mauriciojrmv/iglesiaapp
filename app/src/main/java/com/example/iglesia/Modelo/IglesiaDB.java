package com.example.iglesia.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class IglesiaDB extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "iglesia.db";
    private static final int VERSION_BD = 2;
    public static final String TABLA_MIEMBRO="CREATE TABLE MIEMBRO(ID INTEGER PRIMARY KEY , NOMBRE TEXT, APELLIDO TEXT, CARNET TEXT, CELULAR TEXT, FECHA TEXT)";

    public static final String TABLA_CARGO="CREATE TABLE CARGO(ID INTEGER PRIMARY KEY , TITULO TEXT, DESCRIPCION TEXT)";

    public static final String TABLA_ACTIVIDAD = "CREATE TABLE ACTIVIDAD (ID INTEGER PRIMARY KEY, NOMBRE TEXT, FECHA TEXT, HORA TEXT)";

    public static final String TABLA_OFRENDA = "CREATE TABLE OFRENDA (ID INTEGER PRIMARY KEY, MONTO REAL, FECHA TEXT, MIEMBRO_ID INTEGER, ACTIVIDAD_ID INTEGER, FOREIGN KEY(MIEMBRO_ID) REFERENCES MIEMBRO(ID), FOREIGN KEY(ACTIVIDAD_ID) REFERENCES ACTIVIDAD(ID))";

    public static final String TABLA_ORGANIZACION = "CREATE TABLE ORGANIZACION (ID INTEGER PRIMARY KEY, NOMBRE TEXT, FECHA TEXT, MIEMBRO_ID INTEGER, CARGO_ID INTEGER, FOREIGN KEY(MIEMBRO_ID) REFERENCES MIEMBRO(ID), FOREIGN KEY(CARGO_ID) REFERENCES CARGO(ID))";

    public static final String TABLA_CATPARENTESCO = "CREATE TABLE CATPARENTESCO (ID INTEGER PRIMARY KEY, TITULO TEXT, DESCRIPCION TEXT)";

    public static final String TABLA_PARENTESCO = "CREATE TABLE PARENTESCO (ID INTEGER PRIMARY KEY, FECHA TEXT, MIEMBRO1_ID INTEGER, CATPARENTESCO_ID INTEGER, MIEMBRO2_ID INTEGER, FOREIGN KEY(MIEMBRO1_ID) REFERENCES MIEMBRO(ID), FOREIGN KEY(CATPARENTESCO_ID) REFERENCES CATPARENTESCO(ID), FOREIGN KEY(MIEMBRO2_ID) REFERENCES MIEMBRO(ID))";

    public IglesiaDB(@Nullable Context context){
        super(context, NOMBRE_BD, null, VERSION_BD );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_MIEMBRO);
        sqLiteDatabase.execSQL(TABLA_CARGO);
        sqLiteDatabase.execSQL(TABLA_ACTIVIDAD);
        sqLiteDatabase.execSQL(TABLA_OFRENDA);
        sqLiteDatabase.execSQL(TABLA_ORGANIZACION);
        sqLiteDatabase.execSQL(TABLA_CATPARENTESCO);
        sqLiteDatabase.execSQL(TABLA_PARENTESCO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLA_MIEMBRO + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLA_CARGO + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLA_ACTIVIDAD + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLA_OFRENDA + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLA_ORGANIZACION + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLA_CATPARENTESCO + "'");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLA_PARENTESCO + "'");
        onCreate(sqLiteDatabase);
    }
}
