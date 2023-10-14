package com.example.iglesia.Controlador.Ofrenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iglesia.R;
import com.example.iglesia.Modelo.Actividad.ClaseActividad;
import com.example.iglesia.Modelo.Miembro.ClaseMiembro;
import com.example.iglesia.Modelo.Ofrenda.ClaseOfrenda;

import java.util.List;

public class OfrendaAdaptador extends RecyclerView.Adapter<OfrendaAdaptador.ViewHolder>  {

    //Lista de los objetos contactos
    public List<ClaseOfrenda> ofrendaLista;
    //LSITA PAR MOSTRAR EL NOMBRE APARTIR DEL ID
    private List<ClaseMiembro> miembroLista; // Agrega esta lista de contactos
    private List<ClaseActividad> actividadLista; // Agrega esta lista de contactos


    public OfrendaAdaptador(List<ClaseOfrenda> ofrendaLista, List<ClaseMiembro> miembroLista, List<ClaseActividad> actividadLista) {
        this.ofrendaLista = ofrendaLista;
        this.miembroLista = miembroLista; // Asigna la lista de contactos recibida
        this.actividadLista = actividadLista; // Asigna la lista de categorías recibida
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        //varaibles de la vista que van a ser recicladas
        private TextView id, monto, fechaOfrenda, miembro_id, actividad_id;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.textViewIdOfrenda);
            monto = (TextView) itemView.findViewById(R.id.textViewMontoOfrenda);
            fechaOfrenda = (TextView) itemView.findViewById(R.id.textViewfechaCreacionOfrenda);
            miembro_id = (TextView) itemView.findViewById(R.id.textViewMiembro_id);
            actividad_id = (TextView) itemView.findViewById(R.id.textViewActividad_id);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ofrenda, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(ofrendaLista.get(position).getId()));
        holder.monto.setText(String.valueOf(ofrendaLista.get(position).getMonto()));
        holder.fechaOfrenda.setText(ofrendaLista.get(position).getFechaOfrenda());
        //holder.contacto_id.setText(String.valueOf(prestamoLista.get(position).getContacto_id()));
        holder.miembro_id.setText(obtenerNombreMiembro(ofrendaLista.get(position).getMiembro_id())); // Obtener el nombre del contacto
        //holder.categoria_id.setText(String.valueOf(prestamoLista.get(position).getCategoria_id()));
        holder.actividad_id.setText(obtenerNombreActividad(ofrendaLista.get(position).getActividad_id())); // Obtener el nombre de la categoría
    }

    @Override
    public int getItemCount() {
        return ofrendaLista.size();
    }

    //FUNCION PAR MOSTRAR EL NOMBRE APARTIR DEL ID
    private String obtenerNombreMiembro(int miembroId) {
        for (ClaseMiembro miembro : miembroLista) {
            if (miembro.getId() == miembroId) {
                return miembro.getNombre();
            }
        }
        return ""; // Si no se encuentra el contacto, retorna una cadena vacía
    }

    //FUNCION PAR MOSTRAR LA CATEGORIA APARTIR DEL ID
    private String obtenerNombreActividad(int actividadId) {
        for (ClaseActividad actividad : actividadLista) {
            if (actividad.getId() == actividadId) {
                return actividad.getNombre();
            }
        }
        return ""; // Si no se encuentra la categoría, retorna una cadena vacía
    }


}
