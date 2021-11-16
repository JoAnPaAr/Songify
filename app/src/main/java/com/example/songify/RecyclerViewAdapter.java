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
import com.example.songify.roomdb.Cancion;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>implements View.OnClickListener {

    List<Cancion> cancionList;
    Context context;
    private View.OnClickListener listener;


    public RecyclerViewAdapter(List<Cancion> cancionList, Context context) {
        this.cancionList = cancionList;
        this.context = context;
    }

    public RecyclerViewAdapter(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cancion, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_titulo.setText(cancionList.get(position).getTitle());
        holder.tv_artista.setText(cancionList.get(position).getArtist());
        holder.tv_duracion.setText(cancionList.get(position).getDuration());
        //Se emplea glide para insertar la imagen en iv_cancion_img
        Glide.with(this.context).load(cancionList.get(position).getPicture()).into(holder.iv_cancion);
        //Actualizar favoritos
        if (cancionList.get(position).isFavorito()) {
            holder.btn_fav.setBackgroundResource(R.drawable.img_star_on);
        } else {
            holder.btn_fav.setBackgroundResource(R.drawable.img_star);
        }
        holder.btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancion cancionItem = cancionList.get(holder.getAdapterPosition());

                if (cancionItem.isFavorito()) {
                    cancionItem.setFavorito(false);
                    holder.btn_fav.setBackgroundResource(R.drawable.img_star);

                } else {
                    cancionItem.setFavorito(true);
                    holder.btn_fav.setBackgroundResource(R.drawable.img_star_on);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cancionList.size();
    }

    //Metodo que escucha el onClick
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_cancion;
        TextView tv_titulo;
        TextView tv_artista;
        TextView tv_duracion;
        ImageView btn_fav;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cancion = itemView.findViewById(R.id.iv_cancion_img);
            tv_titulo = itemView.findViewById(R.id.tv_cancion_titulo);
            tv_artista = itemView.findViewById(R.id.tv_cancion_artista);
            tv_duracion = itemView.findViewById(R.id.tv_cancion_duracion);
            btn_fav = itemView.findViewById(R.id.iv_fav_cancion);
        }
    }
}
