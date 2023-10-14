package com.example.iglesia.Controlador.Miembro;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iglesia.Modelo.Miembro.ClaseMiembro;
import com.example.iglesia.R;


import java.util.List;

public class MiembroAdaptador extends RecyclerView.Adapter<MiembroAdaptador.ViewHolder> {

    //Lista de los objetos contactos
    public List<ClaseMiembro> miembroLista;

    public MiembroAdaptador(List<ClaseMiembro> miembroLista) {
        this.miembroLista = miembroLista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        //varaibles de la vista que van a ser recicladas

        private TextView id, nombre, apellido, carnet, telefono;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.textViewId);
            nombre = (TextView) itemView.findViewById(R.id.textViewNombre);
            apellido = (TextView) itemView.findViewById(R.id.textViewApellido);
            carnet = (TextView) itemView.findViewById(R.id.textViewCarnet);
            telefono = (TextView) itemView.findViewById(R.id.textViewTelefono);

        }
    }

    //INFLA LA VISTA DEL ITEM
    @NonNull
    @Override
    public MiembroAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_miembro, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //CARGA CON LOS VALORES DEL ITEM
    @Override
    public void onBindViewHolder(@NonNull MiembroAdaptador.ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(miembroLista.get(position).getId()));
        holder.nombre.setText(miembroLista.get(position).getNombre());
        holder.apellido.setText(miembroLista.get(position).getApellido());
        holder.carnet.setText(String.valueOf(miembroLista.get(position).getCarnet()));
        holder.telefono.setText(miembroLista.get(position).getTelefono());

    }

    //CANTIDAD DE ITEMS QUE VAN A HACER CARGADOS
    @Override
    public int getItemCount() {
        return miembroLista.size();
    }
}
