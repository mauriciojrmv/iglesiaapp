package com.example.iglesia.Controlador.Organizacion;

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

import com.example.iglesia.Controlador.Cargo.Cargo;
import com.example.iglesia.Controlador.Miembro.Miembro;
import com.example.iglesia.Modelo.Cargo.ClaseCargo;
import com.example.iglesia.Modelo.Cargo.ModeloCargo;
import com.example.iglesia.Modelo.Miembro.ClaseMiembro;
import com.example.iglesia.Modelo.Miembro.ModeloMiembro;
import com.example.iglesia.Modelo.Organizacion.ClaseOrganizacion;
import com.example.iglesia.Modelo.Organizacion.ModeloOrganizacion;
import com.example.iglesia.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Organizacion extends AppCompatActivity {

    EditText etIdOrganizacion;
    EditText etNombreOrganizacion;
    EditText etDPFechaOrganizacion;

    Spinner spinnerMiembro;
    Spinner spinnerCargo;


    Button btnAgregarOrganizacion;
    Button btnBuscarOrganizacion;
    Button btnEliminarOrganizacion;
    Button btnEditarOrganizacion;
    Button btnMostrarOrganizaciones;
    Button btnlimpiarOrganizacion;
    Button btnDPFechaOrganizacion;


    List<ClaseMiembro> listaMiembros;
    List<ClaseCargo> listaCargos;


    private int anio, mes, dia;
    String fechaOrganizacionSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizacion);

        etIdOrganizacion = findViewById(R.id.editTextIdOrganizacion);
        etNombreOrganizacion = findViewById(R.id.editTextNombreOrganizacion);
        etDPFechaOrganizacion = findViewById(R.id.editTextDPFechaOrganizacion);
        btnDPFechaOrganizacion = findViewById(R.id.btnDPFechaOrganizacion);

        btnAgregarOrganizacion = findViewById(R.id.btnAddOrganizacion);
        btnBuscarOrganizacion = findViewById(R.id.btnBuscarOrganizacion);
        btnEditarOrganizacion = findViewById(R.id.btnEditOrganizacion);
        btnEliminarOrganizacion = findViewById(R.id.btnDeleteOrganizacion);
        btnMostrarOrganizaciones = findViewById(R.id.btnShowOrganizacion);
        btnlimpiarOrganizacion = findViewById(R.id.btnSLimpiarOrganizacion);

        spinnerMiembro = findViewById(R.id.spinnerMiembrosOrganizacion);
        spinnerCargo = findViewById(R.id.spinnerCargosOrganizacion);




        // CREAR INSTANCIA DEL MODELO
        ModeloOrganizacion modeloOrganizacion = new ModeloOrganizacion(Organizacion.this);
        ModeloMiembro modeloMiembro = new ModeloMiembro(Organizacion.this);
        ModeloCargo modeloCargo = new ModeloCargo(Organizacion.this);

        //BOTON FECHA PRESTAMO CREACION
        btnDPFechaOrganizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                anio = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Organizacion.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        fechaOrganizacionSeleccionada = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        etDPFechaOrganizacion.setText(fechaOrganizacionSeleccionada);
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
        listaCargos = modeloCargo.mostrarCargos();
        List<String> titulosCargos = new ArrayList<>();
        for (ClaseCargo cargo : listaCargos) {
            titulosCargos.add(cargo.getTitulo());
        }
        ArrayAdapter<String> adapterCargo = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, titulosCargos);
        spinnerCargo.setAdapter(adapterCargo);




        // GENERAR EVENTO DE AGREGAR ORGANIZACION
        btnAgregarOrganizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseMiembro miembroSeleccionado = listaMiembros.get(spinnerMiembro.getSelectedItemPosition());
                ClaseCargo cargoSeleccionada = listaCargos.get(spinnerCargo.getSelectedItemPosition());
                modeloOrganizacion.agregarOrganizacion(
                        Integer.valueOf(etIdOrganizacion.getText().toString()),
                        etNombreOrganizacion.getText().toString(),
                        etDPFechaOrganizacion.getText().toString(),
                        miembroSeleccionado.getId(),
                        cargoSeleccionada.getId()
                );
                limpiar();
                Toast.makeText(getApplicationContext(), "EL USUARIO SE AGREGO CORRECTAMENTE A ORGANIZACION", Toast.LENGTH_SHORT).show();
            }
        });

        // Actividad de mostrar prestamos

        btnMostrarOrganizaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mostrarCargos = new Intent(getApplicationContext(), OrganizacionMostrar.class);
                startActivity(mostrarCargos);
            }
        });

        // GENERAR EVENTO DE BUSCAR PRESTAMO
        btnBuscarOrganizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseOrganizacion.Builder organizacionBuilder = new ClaseOrganizacion.Builder();
                int id = Integer.valueOf(etIdOrganizacion.getText().toString());
                modeloOrganizacion.buscarOrganizacion(organizacionBuilder, id);

                ClaseOrganizacion organizacion = organizacionBuilder.build();

                etNombreOrganizacion.setText(String.valueOf(organizacion.getNombre()));
                etDPFechaOrganizacion.setText(organizacion.getFecha());

                // Seleccionar el contacto correspondiente en el Spinner
                int posicionMiembro = obtenerPosicionMiembro(organizacion.getMiembro_id());
                spinnerMiembro.setSelection(posicionMiembro);

                // Seleccionar la categoría correspondiente en el Spinner
                int posicionCargo = obtenerPosicionCargos(organizacion.getCargo_id());
                spinnerCargo.setSelection(posicionCargo);
            }
        });


        // GENERAR EVENTO DE EDITAR PRESTAMO
        btnEditarOrganizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClaseMiembro contactoSeleccionado = listaMiembros.get(spinnerMiembro.getSelectedItemPosition());
                ClaseCargo categoriaSeleccionada = listaCargos.get(spinnerCargo.getSelectedItemPosition());
                modeloOrganizacion.editarOrganizacion(
                        Integer.valueOf(etIdOrganizacion.getText().toString()),
                        etNombreOrganizacion.getText().toString(),
                        etDPFechaOrganizacion.getText().toString(),
                        contactoSeleccionado.getId(),
                        categoriaSeleccionada.getId()
                );
                limpiar();
                Toast.makeText(getApplicationContext(), "LOS DATOS SE ACTUALIZARON CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        // GENERAR EVENTO DE ELIMINAR PRESTAMO
        btnEliminarOrganizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modeloOrganizacion.eliminarOrganizacion(Integer.valueOf(etIdOrganizacion.getText().toString()));
                limpiar();
                Toast.makeText(getApplicationContext(), "LOS DATOS SE ELIMINARON CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        // GENERAR EVENTO DE LIMPIAR LOS CAMPOS
        btnlimpiarOrganizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiar();
            }
        });
    }


    private void limpiar() {
        etIdOrganizacion.setText("");
        etNombreOrganizacion.setText("");
        etDPFechaOrganizacion.setText("");
    }
    private int obtenerPosicionMiembro(int miembroId) {
        for (int i = 0; i < listaMiembros.size(); i++) {
            if (listaMiembros.get(i).getId() == miembroId) {
                return i;
            }
        }
        return 0; // Si no se encuentra, se selecciona la posición 0 por defecto
    }

    private int obtenerPosicionCargos(int cargoId) {
        for (int i = 0; i < listaCargos.size(); i++) {
            if (listaCargos.get(i).getId() == cargoId) {
                return i;
            }
        }
        return 0; // Si no se encuentra, se selecciona la posición 0 por defecto
    }


}