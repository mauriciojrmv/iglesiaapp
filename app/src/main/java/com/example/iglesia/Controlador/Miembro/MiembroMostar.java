package com.example.iglesia.Controlador.Miembro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.iglesia.Modelo.Miembro.ModeloMiembro;
import com.example.iglesia.Modelo.Miembro.ModeloMiembroProxy;
import com.example.iglesia.R;

public class MiembroMostar extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MiembroAdaptador miembroAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miembro_mostar);


        recyclerView = (RecyclerView) findViewById(R.id.reciclerViewMiembro);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //INSTACIA DE NUESTRO MODELO PARA QUE UTILICE EL METODO MOSTRAR CONTACTO
        ModeloMiembroProxy modeloMiembroProxy = new ModeloMiembroProxy(getApplicationContext());


        miembroAdaptador = new MiembroAdaptador(modeloMiembroProxy.mostrarMiembros());
        recyclerView.setAdapter(miembroAdaptador);
    }


}