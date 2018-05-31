package org.uaq.app.learn.com.littleapp_rentice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.ActividadViewHolder> {
    private List<Actividad> items;
    public static class ActividadViewHolder extends RecyclerView.ViewHolder{
        public TextView actividad;
        public TextView nombre;
        public RatingBar calif;

        public ActividadViewHolder(View view){
            super(view);
            actividad = view.findViewById(R.id.card_act_name);
            nombre = view.findViewById(R.id.card_r_name);
            calif = view.findViewById(R.id.card_rating);
        }
    }
    public ActividadAdapter(List<Actividad> actividadList){
        this.items = actividadList;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ActividadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticias_cardview,parent,
                false);
        return new ActividadViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ActividadViewHolder holder, int position) {
        holder.nombre.setText(items.get(position).getActividad());
        holder.actividad.setText(items.get(position).getRealizador());
        //holder.actividad.setCompoundDrawables(DrR.mipmap.ic_launcher,null,null,null);
        holder.calif.setRating(items.get(position).getRateActividad());


    }
}
