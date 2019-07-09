package com.studio.suku.submission3.db;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio.suku.submission3.R;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {

    private ArrayList<Favorite> favoriteArrayList = new ArrayList<>();
    private Activity activity;

    public FavoriteAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Favorite> getFavoriteArrayList(){
        return favoriteArrayList;
    }

    public void setFavoriteArrayList(ArrayList<Favorite> favoriteArrayList) {

        if (favoriteArrayList.size() > 0){
            this.favoriteArrayList.clear();
        }

        this.favoriteArrayList.addAll(favoriteArrayList);
        notifyDataSetChanged();

    }

    //There Is Function To manipulation data

    public void addItem(Favorite favorite){
        this.favoriteArrayList.add(favorite);
        notifyItemInserted(favoriteArrayList.size() - 1);
    }

    public void deleteItem(int id){
        this.favoriteArrayList.remove(id);
        notifyItemRemoved(id);
        notifyItemRangeChanged(id, favoriteArrayList.size());
    }



    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_favorite, viewGroup, false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder favoriteHolder, int i) {
        favoriteHolder.title.setText(favoriteArrayList.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return favoriteArrayList.size();
    }

    public class FavoriteHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        public FavoriteHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_favorite);
            img = itemView.findViewById(R.id.img_favorite);
        }
    }
}
