package com.example.iglesia.Controlador.Organizacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iglesia.Modelo.Cargo.ClaseCargo;
import com.example.iglesia.Modelo.Cargo.ModeloCargo;
import com.example.iglesia.Modelo.Miembro.ClaseMiembro;
import com.example.iglesia.Modelo.Miembro.ModeloMiembro;
import com.example.iglesia.Modelo.Organizacion.ModeloOrganizacion;
import com.example.iglesia.R;

import java.util.List;


public class OrganizacionMostrar extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrganizacionAdaptador organizacionAdaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizacion_mostrar);

        recyclerView = findViewById(R.id.reciclerViewOrganizacion);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //INSTACIA DE NUESTRO MODELO PARA QUE UTILICE EL METODO MOSTRAR PRESTAMO
        ModeloOrganizacion modeloOrganizacion = new ModeloOrganizacion(getApplicationContext());

        // USO DEL MODELO PAR MOSTRAR EL NOMBRE APARTIR DEL ID
        ModeloMiembro modeloMiembro = new ModeloMiembro(getApplicationContext());
        List<ClaseMiembro> listaMiembros = modeloMiembro.mostrarMiembros();

        // USO DEL MODELO PAR MOSTRAR EL TITULO CATEGORIA APARTIR DEL ID
        ModeloCargo modeloCargo = new ModeloCargo(getApplicationContext());
        List<ClaseCargo> listaCargos = modeloCargo.mostrarCargos();

        organizacionAdaptador = new OrganizacionAdaptador(modeloOrganizacion.mostrarOrganizaciones(),listaMiembros, listaCargos);
        recyclerView.setAdapter(organizacionAdaptador);

    }
}