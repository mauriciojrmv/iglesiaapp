package com.example.iglesia.Controlador.Actividad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iglesia.Modelo.Actividad.ModeloActividad;
import com.example.iglesia.R;

public class ActividadMostrar extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ActividadAdaptador actividadAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_mostrar);

        recyclerView = findViewById(R.id.reciclerViewActividad);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //INSTACIA DE NUESTRO MODELO PARA QUE UTILICE EL METODO MOSTRAR EVENTO
        ModeloActividad modeloActividad = new ModeloActividad(getApplicationContext());

        actividadAdaptador = new ActividadAdaptador(modeloActividad.mostrarActividades());
        recyclerView.setAdapter(actividadAdaptador);

    }
}