package org.uaq.app.learn.com.littleapp_rentice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.PostsHolder> {
    private List<Posts> items;
    public static class PostsHolder extends RecyclerView.ViewHolder{
        public TextView fecha;
        public TextView tiempo;
        public RatingBar aciertos;
        public RatingBar errores;

        public PostsHolder(View view){
            super(view);
            fecha = view.findViewById(R.id.card_date);
            tiempo = view.findViewById(R.id.card_tiempo);
            aciertos = view.findViewById(R.id.card_aciertos);
            errores = view.findViewById(R.id.card_errores);
        }
    }
    public ActividadAdapter(List<Posts> actividadList){
        this.items = actividadList;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public PostsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticias_cardview,parent,
                false);
        return new PostsHolder(v);
    }

    @Override
    public void onBindViewHolder(PostsHolder holder, int position) {
        holder.tiempo.setText(items.get(position).getTiempo());
        holder.fecha.setText(items.get(position).getFecha());
        holder.aciertos.setRating(Float.parseFloat(items.get(position).getAciertos()));
        holder.aciertos.setRating(Float.parseFloat(items.get(position).getErrores()));
    }
}
