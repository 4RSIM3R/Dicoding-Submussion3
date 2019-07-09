package com.studio.suku.submission3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private ArrayList<Items> itemsList = new ArrayList<>();

    Context context;

    private OnRowClicked onRowClicked;

    public interface OnRowClicked{
        void OnClicked(int position);
    }

    public void setOnRowClicked(OnRowClicked RowClicked){
        onRowClicked = RowClicked;
    }






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
        final int position = myViewHolder.getAdapterPosition();

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
            img = itemView.findViewById(R.id.img_item_photo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRowClicked != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onRowClicked.OnClicked(position);
                        }
                    }
                }
            });
        }

        //There Is A Function To Bind Data
        public void bind(Items items){
            Picasso.get().load(items.getPath_img()).into(img);
        }
    }
}
