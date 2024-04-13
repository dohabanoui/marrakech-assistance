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
import com.lst.marrakechassistance.Model.Restaurant;
import com.lst.marrakechassistance.R;

import java.util.List;

public class RestaurantResultAdapter extends RecyclerView.Adapter<RestaurantResultAdapter.MyViewHolder> {

    // private Context rContext;
    List<Restaurant> rData;
    LayoutInflater inflater;
    Context rContext ;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public RestaurantResultAdapter(List<Restaurant> rData, Context rContext) {
        this.rData = rData;
        this.rContext=rContext;
        this.inflater = LayoutInflater.from(rContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.resto_items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(rData.get(position).getName());
        holder.adress.setText(rData.get(position).getAddress());
        Glide.with(holder.itemView.getContext())
                .load(rData.get(position).getImgUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.hotel_ph)
                        .error(R.drawable.hotel_ph))
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name ;
        TextView adress ;
        ImageView img ;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.res_name);
            this.adress = itemView.findViewById(R.id.res_adress);
            this.img = itemView.findViewById(R.id.res_img);
        }
    }
}
