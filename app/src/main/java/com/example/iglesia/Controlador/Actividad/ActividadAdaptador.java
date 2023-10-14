package com.example.iglesia.Controlador.Actividad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iglesia.Modelo.Actividad.ClaseActividad;
import com.example.iglesia.R;

import java.util.List;

public class ActividadAdaptador extends RecyclerView.Adapter<ActividadAdaptador.ViewHolder> {

    //Lista de los objetos contactos
    public List<ClaseActividad> actividadLista;

    public ActividadAdaptador(List<ClaseActividad> actividadLista) {
        this.actividadLista = actividadLista;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        //varaibles de la vista que van a ser recicladas
        private TextView id, nombre, fecha, hora;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.textViewIdActividad);
            nombre = (TextView) itemView.findViewById(R.id.textViewNombreActividad);
            fecha = (TextView) itemView.findViewById(R.id.textViewFechaActividad);
            hora = (TextView) itemView.findViewById(R.id.textViewHoraaActividad);
        }
    }


    @NonNull
    @Override
    public ActividadAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actividad, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadAdaptador.ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(actividadLista.get(position).getId()));
        holder.nombre.setText(actividadLista.get(position).getNombre());
        holder.fecha.setText(actividadLista.get(position).getFecha());
        holder.hora.setText(actividadLista.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return actividadLista.size();
    }


}
