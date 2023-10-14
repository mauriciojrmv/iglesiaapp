package com.example.iglesia.Controlador.Cargo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iglesia.Modelo.Cargo.ModeloCargo;
import com.example.iglesia.R;

public class CargoMostrar extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CargoAdaptador cargoAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_mostrar);

        recyclerView = findViewById(R.id.reciclerViewCargo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //INSTACIA DE NUESTRO MODELO PARA QUE UTILICE EL METODO MOSTRAR CATEGORIA
        ModeloCargo modeloCargo = new ModeloCargo(getApplicationContext());

        cargoAdaptador = new CargoAdaptador(modeloCargo.mostrarCargos());
        recyclerView.setAdapter(cargoAdaptador);

    }
}