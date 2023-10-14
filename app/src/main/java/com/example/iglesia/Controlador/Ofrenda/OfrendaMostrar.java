package com.example.iglesia.Controlador.Ofrenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iglesia.Controlador.Ofrenda.OfrendaAdaptador;
import com.example.iglesia.Modelo.Actividad.ModeloActividad;
import com.example.iglesia.Modelo.Actividad.ClaseActividad;
import com.example.iglesia.Modelo.Miembro.ClaseMiembro;
import com.example.iglesia.Modelo.Miembro.ModeloMiembro;
import com.example.iglesia.Modelo.Ofrenda.ModeloOfrenda;
import com.example.iglesia.R;

import java.util.List;


public class OfrendaMostrar extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OfrendaAdaptador ofrendaAdaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ofrenda_mostrar);

        recyclerView = findViewById(R.id.reciclerViewOfrenda);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //INSTACIA DE NUESTRO MODELO PARA QUE UTILICE EL METODO MOSTRAR PRESTAMO
        ModeloOfrenda modeloOfrenda = new ModeloOfrenda(getApplicationContext());

        // USO DEL MODELO PAR MOSTRAR EL NOMBRE APARTIR DEL ID
        ModeloMiembro modeloMiembro = new ModeloMiembro(getApplicationContext());
        List<ClaseMiembro> listaMiembros = modeloMiembro.mostrarMiembros();

        // USO DEL MODELO PAR MOSTRAR EL TITULO CATEGORIA APARTIR DEL ID
        ModeloActividad modeloActividad = new ModeloActividad(getApplicationContext());
        List<ClaseActividad> listaActividades = modeloActividad.mostrarActividades();

        ofrendaAdaptador = new OfrendaAdaptador(modeloOfrenda.mostrarOfrendas(),listaMiembros, listaActividades);
        recyclerView.setAdapter(ofrendaAdaptador);

    }
}