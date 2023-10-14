package com.example.iglesia.Controlador.CatParentesco;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iglesia.R;

import java.util.List;

public class CatParentescoAdaptador extends RecyclerView.Adapter<CatParentescoAdaptador.ViewHolder>{

    //Lista de los objetos contactos
    public List<com.example.iglesia.Modelo.Cargo.ClaseCargo> cargoLista;

    public CatParentescoAdaptador(List<com.example.iglesia.Modelo.Cargo.ClaseCargo> contactoLista) {
        this.cargoLista = contactoLista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //varaibles de la vista que van a ser recicladas
        private TextView id, titulo, descripcion;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.textViewIdCargo);
            titulo = (TextView) itemView.findViewById(R.id.textViewTituloCargo);
            descripcion = (TextView) itemView.findViewById(R.id.textViewDescripcionCargo);
        }
    }

    @NonNull
    @Override
    public CatParentescoAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catparentesco, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatParentescoAdaptador.ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(cargoLista.get(position).getId()));
        holder.titulo.setText(cargoLista.get(position).getTitulo());
        holder.descripcion.setText(cargoLista.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return cargoLista.size();
    }

}
