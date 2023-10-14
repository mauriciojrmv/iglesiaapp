package com.example.iglesia.Controlador.Miembro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iglesia.Modelo.Miembro.ClaseMiembro;
import com.example.iglesia.Modelo.Miembro.ModeloMiembroProxy;
import com.example.iglesia.R;

public class Miembro extends AppCompatActivity {

    EditText etIdMiembro;
    EditText etNombreMiembro;
    EditText etApellidoMiembro;
    EditText etCarnetMiembro;
    EditText etTelefonoMiembro;

    Button btnAgregarMiembro, btnMostrarMiembro, btnBuscarMiembro, btnEditarMiembro, btnEliminarMiembro, btnLimpiar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miembro);


        //Asociamos nuestros elementos con los del formulario (vista)

        etIdMiembro = findViewById(R.id.editTextId);
        etNombreMiembro = findViewById(R.id.editTextNombreMiembro);
        etApellidoMiembro = findViewById(R.id.editTextApellidoMiembro);
        etCarnetMiembro = findViewById(R.id.editTextCarnetMiembro);
        etTelefonoMiembro = findViewById(R.id.editTextTelefonoMiembro);

        btnAgregarMiembro = findViewById(R.id.btnAddMiembro);
        btnMostrarMiembro = findViewById(R.id.btnShowMiembro);
        btnBuscarMiembro = findViewById(R.id.btnBuscarMiembro);
        btnEditarMiembro = findViewById(R.id.btnEditMiembro);
        btnEliminarMiembro = findViewById(R.id.btnDeleteMiembro);
        btnLimpiar = findViewById(R.id.btnLimpiar);


        //CREAMOS LA INSTACIA DEL MODELO
        final ModeloMiembroProxy modeloMiembroProxy = new ModeloMiembroProxy(Miembro.this);

        //GENERAR EL EVENTO DE AGREGAR CONTACTO
        btnAgregarMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloMiembroProxy.agregarMiembro(Integer.valueOf(etIdMiembro.getText().toString()),etNombreMiembro.getText().toString(), etApellidoMiembro.getText().toString(), Integer.valueOf(etCarnetMiembro.getText().toString()), etTelefonoMiembro.getText().toString());
                if (modeloMiembroProxy.isAgregadoCorrectamente()) {
                    Toast.makeText(getApplicationContext(), "SE AGREGÓ CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    limpiar();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL AGREGAR EL MIEMBRO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //GENERAR EL EVENTO DE MOSTRAR CONTACTO
        btnMostrarMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarMiembros = new Intent(getApplicationContext(), MiembroMostar.class);
                startActivity(mostrarMiembros);
            }
        });

        //GENERAR EVENTO DE BUSCAR CONTACTO
        btnBuscarMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseMiembro miembro = new ClaseMiembro();
                String textid = etIdMiembro.getText().toString();
                int id = Integer.valueOf(textid);
                modeloMiembroProxy.buscarMiembro(miembro, id);
                etNombreMiembro.setText(miembro.getNombre());
                etApellidoMiembro.setText(miembro.getApellido());
                etCarnetMiembro.setText(String.valueOf(miembro.getCarnet()));
                etTelefonoMiembro.setText(miembro.getTelefono());

            }
        });

        //GENERAR EVENTO DE EDITAR CONTACTO
        btnEditarMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloMiembroProxy.editarMiembro(Integer.valueOf(etIdMiembro.getText().toString()), etNombreMiembro.getText().toString(), etApellidoMiembro.getText().toString(), Integer.valueOf(etCarnetMiembro.getText().toString()),etTelefonoMiembro.getText().toString());
                if (modeloMiembroProxy.isAgregadoCorrectamente()) {
                    Toast.makeText(getApplicationContext(), "LOS DATOS SE ACTUALIZARON CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    limpiar();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL ACTUALIZAR EL CONTACTO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //GENERAR EVENTO DE ELIMINAR CONTACTO
        btnEliminarMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloMiembroProxy.eliminarMiembro(Integer.valueOf(etIdMiembro.getText().toString()));
                limpiar();
                Toast.makeText(getApplicationContext(), "Los Datos se Eliminaron Correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        //GENERAR EVENTO DE LIMPIAR LOS CAMPOS
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
    }

    private  void limpiar(){
        etIdMiembro.setText("");
        etNombreMiembro.setText("");
        etApellidoMiembro.setText("");
        etCarnetMiembro.setText("");
        etTelefonoMiembro.setText("");
    }
}