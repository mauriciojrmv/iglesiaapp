package com.example.iglesia.Controlador.CatParentesco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iglesia.Modelo.CatParentesco.ClaseCatParentesco;
import com.example.iglesia.Modelo.CatParentesco.ModeloCatParentesco;
import com.example.iglesia.R;

public class CatParentesco extends AppCompatActivity {

    //CONEXION VARIABLES DE LA VISTA
    EditText etIdCatParentesco;
    EditText etTituloCatParentesco;
    EditText etDescripcionCatParentesco;

    Button btnAgregarCatParentesco;
    Button btnEditarCatParentesco;
    Button btnEliminarCatParentesco;
    Button btnMostarCatParentesco;
    Button btnBuscarCatParentesco;
    Button btnLimpiarCatParentesco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catparentesco);

        etIdCatParentesco = findViewById(R.id.editTextIdCargo);
        etTituloCatParentesco = findViewById(R.id.editTextTituloCargo);
        etDescripcionCatParentesco = findViewById(R.id.editTextDescripcionCargo);

        btnAgregarCatParentesco = findViewById(R.id.btnAddCargo);
        btnMostarCatParentesco = findViewById(R.id.btnShowCargo);
        btnBuscarCatParentesco = findViewById(R.id.btnBuscarCargo);
        btnEditarCatParentesco = findViewById(R.id.btnEditCargo);
        btnEliminarCatParentesco = findViewById(R.id.btnDeleteCargo);
        btnLimpiarCatParentesco = findViewById(R.id.btnLimpiarCargo);

        //CREAMOS LA INSTANCIA CON EL MODELO
        ModeloCatParentesco modeloCatParentesco = new ModeloCatParentesco(CatParentesco.this);

        //GENERAR EL EVENTO DE AGREGAR CATEGORIA
        btnAgregarCatParentesco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloCatParentesco.agregarCatParentesco(Integer.valueOf(etIdCatParentesco.getText().toString()), etTituloCatParentesco.getText().toString(), etDescripcionCatParentesco.getText().toString());
                limpiar();
                Toast.makeText(getApplicationContext(), "EL CARGO SE AGREGO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        //GENERAR EL EVENTO DE MOSTRAR CATEGORIAS
        btnMostarCatParentesco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarCatParentescos = new Intent(getApplicationContext(), CatParentescoMostrar.class);
                startActivity(mostrarCatParentescos);
            }
        });

        //GENERAR EVENTO DE BUSCAR CATEGORIA
        btnBuscarCatParentesco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseCatParentesco catParentesco = new ClaseCatParentesco();
                String textid = etIdCatParentesco.getText().toString();
                int id = Integer.valueOf(textid);
                modeloCatParentesco.buscarCatParentescos(catParentesco, id);
                etTituloCatParentesco.setText(catParentesco.getTitulo());
                etDescripcionCatParentesco.setText(catParentesco.getDescripcion());

            }
        });

        //GENERAR EVENTO DE EDITAR CATEGORIA
        btnEditarCatParentesco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloCatParentesco.editarCatParentesco(Integer.valueOf(etIdCatParentesco.getText().toString()), etTituloCatParentesco.getText().toString(), etDescripcionCatParentesco.getText().toString());
                limpiar();
                Toast.makeText(getApplicationContext(), "Los Datos se Actualizaron Correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        //GENERAR EVENTO DE ELIMINAR CATEGORIA
        btnEliminarCatParentesco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloCatParentesco.eliminarCatParentesco(Integer.valueOf(etIdCatParentesco.getText().toString()));
                limpiar();
                Toast.makeText(getApplicationContext(), "Los Datos se Eliminaron Correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        //GENERAR EVENTO DE LIMPIAR LOS CAMPOS
        btnLimpiarCatParentesco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });

    }

    private void limpiar(){
        etIdCatParentesco.setText("");
        etTituloCatParentesco.setText("");
        etDescripcionCatParentesco.setText("");
    }

}