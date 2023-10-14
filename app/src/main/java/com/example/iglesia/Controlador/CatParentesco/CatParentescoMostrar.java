package com.example.iglesia.Controlador.CatParentesco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iglesia.Controlador.CatParentesco.CatParentescoAdaptador;
import com.example.iglesia.Modelo.Cargo.ModeloCargo;
import com.example.iglesia.R;

public class CatParentescoMostrar extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CatParentescoAdaptador catParentescoAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_mostrar);

        recyclerView = findViewById(R.id.reciclerViewCargo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //INSTACIA DE NUESTRO MODELO PARA QUE UTILICE EL METODO MOSTRAR CATEGORIA
        ModeloCargo modeloCargo = new ModeloCargo(getApplicationContext());

        catParentescoAdaptador = new CatParentescoAdaptador(modeloCargo.mostrarCargos());
        recyclerView.setAdapter(catParentescoAdaptador);

    }
}