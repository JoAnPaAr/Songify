package com.example.songify.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.songify.R;
import com.example.songify.data.Cancion;
import com.example.songify.data.CancionDAO;
import com.example.songify.data.CancionDatabase;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {

    LiveData<List<Cancion>> cancionList;
    Context context;
    private View.OnClickListener listener;
    private CancionDAO mCancionDao;


    public RecyclerViewAdapter(LiveData<List<Cancion>> cancionList, Context context) {
        this.cancionList = cancionList;
        this.context = context;
    }

    public RecyclerViewAdapter(LiveData<List<Cancion>> cancionList) {
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
        CancionDatabase cancionDatabase = CancionDatabase.getInstance(this.context);
        holder.tv_titulo.setText(cancionList.getValue().get(position).getTitle());
        holder.tv_artista.setText(cancionList.getValue().get(position).getArtist());
        holder.tv_duracion.setText(cancionList.getValue().get(position).getDuration());
        //Se emplea glide para insertar la imagen en iv_cancion_img
        Glide.with(this.context).load(cancionList.getValue().get(position).getPicture()).into(holder.iv_cancion);
        //Actualizar favoritos
        if (cancionList.getValue().get(position).isFavorito()) {
            holder.btn_fav.setBackgroundResource(R.drawable.img_star_on);
        } else {
            holder.btn_fav.setBackgroundResource(R.drawable.img_star);
        }
        holder.btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancion cancionItem = cancionList.getValue().get(holder.getAdapterPosition());

                if (cancionItem.isFavorito()) {
                    cancionItem.setFavorito(false);
                    holder.btn_fav.setBackgroundResource(R.drawable.img_star);
                    CancionDatabase.databaseWriterExecutor.execute(() -> {
                        cancionDatabase.getDao().update(cancionItem);
                    });
                } else {
                    cancionItem.setFavorito(true);
                    holder.btn_fav.setBackgroundResource(R.drawable.img_star_on);
                    CancionDatabase.databaseWriterExecutor.execute(() -> {
                        cancionDatabase.getDao().update(cancionItem);
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (cancionList.getValue() != null) {
            return cancionList.getValue().size();
        } else {
            return 0;
        }
    }

    //Metodo que escucha el onClick
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void swap(LiveData<List<Cancion>> dataset) {
        cancionList = dataset;
        notifyDataSetChanged();
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
