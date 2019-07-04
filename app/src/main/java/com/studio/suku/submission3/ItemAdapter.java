package com.studio.suku.submission3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private ArrayList<Items> itemsList = new ArrayList<>();

    public void setData(ArrayList<Items> items){
        itemsList.clear();
        itemsList.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup,
                false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.bind(itemsList.get(i));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            img = itemView.findViewById(R.id.img_item_photo);
            txt = itemView.findViewById(R.id.txt_detail);
        }

        //There Is A Function To Bind Data
        public void bind(Items items){
            txt.setText("Bisa");
        }
    }
}
