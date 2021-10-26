package com.example.songify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.songify.model.Cancion;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<Cancion> cancionList;
    Context context;

    public RecyclerViewAdapter(List<Cancion> cancionList, Context context) {
        this.cancionList = cancionList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cancion, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_titulo.setText(cancionList.get(position).getTitle());
        holder.tv_artista.setText(cancionList.get(position).getArtist());
        holder.tv_duracion.setText(cancionList.get(position).getDuration());
        //Se emplea glide para insertar la imagen en iv_cancion_img
        Glide.with(this.context).load(cancionList.get(position).getPicture()).into(holder.iv_cancion);

    }

    @Override
    public int getItemCount() {
        return cancionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_cancion;
        TextView tv_titulo;
        TextView tv_artista;
        TextView tv_duracion;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cancion = itemView.findViewById(R.id.iv_cancion_img);
            tv_titulo = itemView.findViewById(R.id.tv_cancion_titulo);
            tv_artista = itemView.findViewById(R.id.tv_cancion_artista);
            tv_duracion = itemView.findViewById(R.id.tv_cancion_duracion);
        }
    }

}
