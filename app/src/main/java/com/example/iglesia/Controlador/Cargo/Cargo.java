package com.example.iglesia.Controlador.Cargo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iglesia.Modelo.Cargo.ClaseCargo;
import com.example.iglesia.Modelo.Cargo.ModeloCargo;
import com.example.iglesia.R;

public class Cargo extends AppCompatActivity {

    //CONEXION VARIABLES DE LA VISTA
    EditText etIdCargo;
    EditText etTituloCargo;
    EditText etDescripcionCargo;

    Button btnAgregarCargo;
    Button btnEditarCargo;
    Button btnEliminarCargo;
    Button btnMostarCargo;
    Button btnBuscarCargo;
    Button btnLimpiarCargo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo);

        etIdCargo = findViewById(R.id.editTextIdCargo);
        etTituloCargo = findViewById(R.id.editTextTituloCargo);
        etDescripcionCargo = findViewById(R.id.editTextDescripcionCargo);

        btnAgregarCargo = findViewById(R.id.btnAddCargo);
        btnMostarCargo = findViewById(R.id.btnShowCargo);
        btnBuscarCargo = findViewById(R.id.btnBuscarCargo);
        btnEditarCargo = findViewById(R.id.btnEditCargo);
        btnEliminarCargo = findViewById(R.id.btnDeleteCargo);
        btnLimpiarCargo = findViewById(R.id.btnLimpiarCargo);

        //CREAMOS LA INSTANCIA CON EL MODELO
        ModeloCargo modeloCargo = new ModeloCargo(Cargo.this);

        //GENERAR EL EVENTO DE AGREGAR CATEGORIA
        btnAgregarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloCargo.agregarCargo(Integer.valueOf(etIdCargo.getText().toString()), etTituloCargo.getText().toString(), etDescripcionCargo.getText().toString());
                limpiar();
                Toast.makeText(getApplicationContext(), "EL CARGO SE AGREGO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        //GENERAR EL EVENTO DE MOSTRAR CATEGORIAS
        btnMostarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarCargos = new Intent(getApplicationContext(), CargoMostrar.class);
                startActivity(mostrarCargos);
            }
        });

        //GENERAR EVENTO DE BUSCAR CATEGORIA
        btnBuscarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseCargo cargo = new ClaseCargo();
                String textid = etIdCargo.getText().toString();
                int id = Integer.valueOf(textid);
                modeloCargo.buscarCargo(cargo, id);
                etTituloCargo.setText(cargo.getTitulo());
                etDescripcionCargo.setText(cargo.getDescripcion());

            }
        });

        //GENERAR EVENTO DE EDITAR CATEGORIA
        btnEditarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloCargo.editarCargo(Integer.valueOf(etIdCargo.getText().toString()), etTituloCargo.getText().toString(), etDescripcionCargo.getText().toString());
                limpiar();
                Toast.makeText(getApplicationContext(), "Los Datos se Actualizaron Correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        //GENERAR EVENTO DE ELIMINAR CATEGORIA
        btnEliminarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloCargo.eliminarCargo(Integer.valueOf(etIdCargo.getText().toString()));
                limpiar();
                Toast.makeText(getApplicationContext(), "Los Datos se Eliminaron Correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        //GENERAR EVENTO DE LIMPIAR LOS CAMPOS
        btnLimpiarCargo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });

    }

    private void limpiar(){
        etIdCargo.setText("");
        etTituloCargo.setText("");
        etDescripcionCargo.setText("");
    }

}