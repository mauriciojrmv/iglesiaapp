package com.example.iglesia.Controlador.Ofrenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.iglesia.Modelo.Actividad.ClaseActividad;
import com.example.iglesia.Modelo.Actividad.ModeloActividad;
import com.example.iglesia.Modelo.Miembro.ClaseMiembro;
import com.example.iglesia.Modelo.Miembro.ModeloMiembro;
import com.example.iglesia.Modelo.Ofrenda.ClaseOfrenda;
import com.example.iglesia.Modelo.Ofrenda.ModeloOfrenda;
import com.example.iglesia.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Ofrenda extends AppCompatActivity {

    EditText etIdOfrenda;
    EditText etMontoOfrenda;
    EditText etDPFechaOfrenda;

    Spinner spinnerMiembro;
    Spinner spinnerActividad;


    Button btnAgregarOfrenda;
    Button btnBuscarOfrenda;
    Button btnEliminarOfrenda;
    Button btnEditarOfrenda;
    Button btnMostrarOfrendas;
    Button btnlimpiarOfrenda;
    Button btnDPFechaOfrenda;

    List<ClaseMiembro> listaMiembros;
    List<ClaseActividad> listaActividades;


    private int anio, mes, dia;
    String fechaOfrendaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofrenda);

        etIdOfrenda = findViewById(R.id.editTextIdOfrenda);
        etMontoOfrenda = findViewById(R.id.editTextMontoOfrenda);
        etDPFechaOfrenda = findViewById(R.id.editTextDPFechaOfrenda);

        btnDPFechaOfrenda = findViewById(R.id.btnDPFechaOfrenda);

        btnAgregarOfrenda = findViewById(R.id.btnAddOfrenda);
        btnBuscarOfrenda = findViewById(R.id.btnBuscarOfrenda);
        btnEditarOfrenda = findViewById(R.id.btnEditOfrenda);
        btnEliminarOfrenda = findViewById(R.id.btnDeleteOfrenda);
        btnMostrarOfrendas = findViewById(R.id.btnShowOfrenda);
        btnlimpiarOfrenda = findViewById(R.id.btnSLimpiarOfrenda);

        spinnerMiembro = findViewById(R.id.spinnerMiembrosOfrendas);
        spinnerActividad = findViewById(R.id.spinnerActividadesOfrenda);




        // CREAR INSTANCIA DEL MODELO
        ModeloOfrenda modeloOfrenda = new ModeloOfrenda(Ofrenda.this);
        ModeloMiembro modeloMiembro = new ModeloMiembro(Ofrenda.this);
        ModeloActividad modeloActividad = new ModeloActividad(Ofrenda.this);

        //BOTON FECHA PRESTAMO CREACION
        btnDPFechaOfrenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                anio = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Ofrenda.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        fechaOfrendaSeleccionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        etDPFechaOfrenda.setText(fechaOfrendaSeleccionada);
                    }
                }, anio, mes, dia);
                datePickerDialog.show();
            }
        });
        //fin del boton de FECHA


        // OBTENER LISTA DE CONTACTOS
        listaMiembros = modeloMiembro.mostrarMiembros();
        List<String> nombreMiembros = new ArrayList<>();
        for (ClaseMiembro miembro : listaMiembros) {
            nombreMiembros.add(miembro.getNombre());
        }
        ArrayAdapter<String> adapterMiembro = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nombreMiembros);
        spinnerMiembro.setAdapter(adapterMiembro);

        // OBTENER LISTA DE CATEGORIAS
        listaActividades = modeloActividad.mostrarActividades();
        List<String> titulosActividades = new ArrayList<>();
        for (ClaseActividad actividad : listaActividades) {
            titulosActividades.add(actividad.getNombre());
        }
        ArrayAdapter<String> adapterActividad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, titulosActividades);
        spinnerActividad.setAdapter(adapterActividad);




        // GENERAR EVENTO DE AGREGAR PRESTAMO
        btnAgregarOfrenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseMiembro miembroSeleccionado = listaMiembros.get(spinnerMiembro.getSelectedItemPosition());
                ClaseActividad ActividadSeleccionada = listaActividades.get(spinnerActividad.getSelectedItemPosition());
                modeloOfrenda.agregarOfrenda(
                        Integer.valueOf(etIdOfrenda.getText().toString()),
                        Double.valueOf(etMontoOfrenda.getText().toString()),
                        etDPFechaOfrenda.getText().toString(),
                        miembroSeleccionado.getId(),
                        ActividadSeleccionada.getId()
                );
                limpiar();
                Toast.makeText(getApplicationContext(), "LA OFRENDA SE AGREGO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        // Actividad de mostrar prestamos

        btnMostrarOfrendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarActividades = new Intent(getApplicationContext(), OfrendaMostrar.class);
                startActivity(mostrarActividades);
            }
        });

        // GENERAR EVENTO DE BUSCAR PRESTAMO
        btnBuscarOfrenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseOfrenda.Builder ofrendaBuilder = new ClaseOfrenda.Builder();
                int id = Integer.valueOf(etIdOfrenda.getText().toString());
                modeloOfrenda.buscarOfrenda(ofrendaBuilder, id);

                ClaseOfrenda ofrenda = ofrendaBuilder.build();

                etMontoOfrenda.setText(String.valueOf(ofrenda.getMonto()));
                etDPFechaOfrenda.setText(ofrenda.getFechaOfrenda());

                // Seleccionar el contacto correspondiente en el Spinner
                int posicionMiembro = obtenerPosicionMiembro(ofrenda.getMiembro_id());
                spinnerMiembro.setSelection(posicionMiembro);

                // Seleccionar la categoría correspondiente en el Spinner
                int posicionActividad = obtenerPosicionActividad(ofrenda.getActividad_id());
                spinnerActividad.setSelection(posicionActividad);
            }
        });


        // GENERAR EVENTO DE EDITAR PRESTAMO
        btnEditarOfrenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseMiembro contactoSeleccionado = listaMiembros.get(spinnerMiembro.getSelectedItemPosition());
                ClaseActividad actividadSeleccionada = listaActividades.get(spinnerActividad.getSelectedItemPosition());
                modeloOfrenda.editarOfrenda(
                        Integer.valueOf(etIdOfrenda.getText().toString()),
                        Double.valueOf(etMontoOfrenda.getText().toString()),
                        etDPFechaOfrenda.getText().toString(),
                        contactoSeleccionado.getId(),
                        actividadSeleccionada.getId()
                );
                limpiar();
                Toast.makeText(getApplicationContext(), "LOS DATOS SE ACTUALIZARON CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        // GENERAR EVENTO DE ELIMINAR PRESTAMO
        btnEliminarOfrenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloOfrenda.eliminarOfrenda(Integer.valueOf(etIdOfrenda.getText().toString()));
                limpiar();
                Toast.makeText(getApplicationContext(), "LOS DATOS SE ELIMINARON CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        // GENERAR EVENTO DE LIMPIAR LOS CAMPOS
        btnlimpiarOfrenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
    }


    private void limpiar() {
        etIdOfrenda.setText("");
        etMontoOfrenda.setText("");
        etDPFechaOfrenda.setText("");
    }

    private int obtenerPosicionMiembro(int miembroId) {
        for (int i = 0; i < listaMiembros.size(); i++) {
            if (listaMiembros.get(i).getId() == miembroId) {
                return i;
            }
        }
        return 0; // Si no se encuentra, se selecciona la posición 0 por defecto
    }
    private int obtenerPosicionActividad(int actividadId) {
        for (int i = 0; i < listaActividades.size(); i++) {
            if (listaActividades.get(i).getId() == actividadId) {
                return i;
            }
        }
        return 0; // Si no se encuentra, se selecciona la posición 0 por defecto
    }


}