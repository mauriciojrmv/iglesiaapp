package com.example.iglesia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.iglesia.Controlador.Actividad.Actividad;
import com.example.iglesia.Controlador.Cargo.Cargo;
import com.example.iglesia.Controlador.CatParentesco.CatParentesco;
import com.example.iglesia.Controlador.Miembro.Miembro;
import com.example.iglesia.Controlador.Ofrenda.Ofrenda;
import com.example.iglesia.Controlador.Organizacion.Organizacion;


public class MainActivity extends AppCompatActivity {

    //vARIABLES DE LA VISTA

    GridLayout menuIglesia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
        setContentView(R.layout.activity_main);

        menuIglesia = (GridLayout)findViewById(R.id.menuGrid);

        //Metodo para poder interactuar con las opciones de los cardView
        setSigleEvent(menuIglesia);

    }

    private void setSigleEvent(GridLayout menuIglesia) {
        for (int i = 0; i < menuIglesia.getChildCount(); i++){
            CardView cardView = (CardView) menuIglesia.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (finalI == 0){
                        Intent intent = new Intent(MainActivity.this, Miembro.class);
                        startActivity(intent);
                    }else if(finalI == 1){
                        Intent intent = new Intent(MainActivity.this, Actividad.class);
                        startActivity(intent);
                    } else if (finalI == 2) {
                        Intent intent = new Intent(MainActivity.this, Cargo.class);
                        startActivity(intent);
                    } else if (finalI == 3) {
                        Intent intent = new Intent(MainActivity.this, CatParentesco.class);
                        startActivity(intent);
                    } else if (finalI == 4) {
                        Intent intent = new Intent(MainActivity.this, Ofrenda.class);
                        startActivity(intent);
                    } else if (finalI == 5) {
                        Intent intent = new Intent(MainActivity.this, Organizacion.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "No existe", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}