package com.lst.marrakechassistance.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lst.marrakechassistance.Model.Attraction;
import com.lst.marrakechassistance.R;

import java.util.List;


public class AttractionsAdapter extends RecyclerView.Adapter<AttractionsAdapter.MyViewHolder>{

    List<Attraction> aData;
    LayoutInflater inflater;
    Context aContext ;
    public AttractionsAdapter(List<Attraction> aData, Context aContext) {
        this.aData = aData;
        this.aContext=aContext;
        this.inflater = LayoutInflater.from(aContext);

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.att_items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(aData.get(position).getName());
        holder.adress.setText(aData.get(position).getAdress());
        holder.cat.setText(aData.get(position).getCategory());
        Glide.with(holder.itemView.getContext())
                .load(aData.get(position).getImg())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.hotel_ph)
                        .error(R.drawable.hotel_ph))
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return aData.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name ;
        TextView adress ;
        ImageView img ;
        TextView cat ;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.att_name);
            this.adress = itemView.findViewById(R.id.att_adress);
            this.img = itemView.findViewById(R.id.att_img);
            this.cat = itemView.findViewById(R.id.attcat);
        }
    }
}
