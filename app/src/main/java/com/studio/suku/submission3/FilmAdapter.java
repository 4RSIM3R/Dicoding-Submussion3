package com.studio.suku.submission3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private ArrayList<ItemFilm> itemFilms = new ArrayList<>();

    Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public void setDataFilm(ArrayList<ItemFilm> items){
        itemFilms.clear();
        itemFilms.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup,
                false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder filmViewHolder, int i) {
        filmViewHolder.bind(itemFilms.get(i), i);
        final int position = filmViewHolder.getAdapterPosition();

    }

    @Override
    public int getItemCount() {
        return itemFilms.size();
    }

    public class FilmViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_item_photo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
        //There Is A Function To Bind Data

        public void bind(ItemFilm itemFilm, int i) {
            Picasso.get().load(itemFilm.getPath_img()).into(img);

        }
    }

}
