package com.example.iglesia.Controlador.Organizacion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.iglesia.Modelo.Cargo.ClaseCargo;
import com.example.iglesia.Modelo.Miembro.ClaseMiembro;
import com.example.iglesia.Modelo.Organizacion.ClaseOrganizacion;
import com.example.iglesia.R;

import java.util.List;

public class OrganizacionAdaptador extends RecyclerView.Adapter<OrganizacionAdaptador.ViewHolder>  {

    //Lista de los objetos contactos
    public List<ClaseOrganizacion> organizacionLista;
    //LSITA PAR MOSTRAR EL NOMBRE APARTIR DEL ID
    private List<ClaseMiembro> miembroLista; // Agrega esta lista de contactos
    private List<ClaseCargo> cargoLista; // Agrega esta lista de contactos


    public OrganizacionAdaptador(List<ClaseOrganizacion> organizacionLista, List<ClaseMiembro> miembroLista, List<ClaseCargo> cargoLista) {
        this.organizacionLista = organizacionLista;
        this.miembroLista = miembroLista; // Asigna la lista de contactos recibida
        this.cargoLista = cargoLista; // Asigna la lista de categorías recibida
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        //varaibles de la vista que van a ser recicladas
        private TextView id, nombre, fechaOrganizacion, miembro_id, cargo_id;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.textViewIdOrganizacion);
            nombre = (TextView) itemView.findViewById(R.id.textViewNombreOrganizacion);
            fechaOrganizacion = (TextView) itemView.findViewById(R.id.textViewfechaCreacionOrganizacion);
            miembro_id = (TextView) itemView.findViewById(R.id.textViewMiembro_id);
            cargo_id = (TextView) itemView.findViewById(R.id.textViewCargo_id);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organizacion, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText(String.valueOf(organizacionLista.get(position).getId()));
        holder.nombre.setText(String.valueOf(organizacionLista.get(position).getNombre()));
        holder.fechaOrganizacion.setText(organizacionLista.get(position).getFecha());
        //holder.contacto_id.setText(String.valueOf(prestamoLista.get(position).getContacto_id()));
        holder.miembro_id.setText(obtenerNombreMiembro(organizacionLista.get(position).getMiembro_id())); // Obtener el nombre del contacto
        //holder.categoria_id.setText(String.valueOf(prestamoLista.get(position).getCategoria_id()));
        holder.cargo_id.setText(obtenerNombreCargo(organizacionLista.get(position).getCargo_id())); // Obtener el nombre de la categoría
    }

    @Override
    public int getItemCount() {
        return organizacionLista.size();
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
    private String obtenerNombreCargo(int cargoId) {
        for (ClaseCargo cargo : cargoLista) {
            if (cargo.getId() == cargoId) {
                return cargo.getTitulo();
            }
        }
        return ""; // Si no se encuentra la categoría, retorna una cadena vacía
    }


}
