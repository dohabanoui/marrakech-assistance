package com.lst.marrakechassistance.Adapter;

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

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    List<Restaurant> restaurants;
    private OnItemClickListener onItemClickListener;

    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_restaurant,parent,false);
        return new ViewHolder(inflate);
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant currentRest = restaurants.get(position);
        holder.restaurantName.setText(currentRest.getName());
        holder.restaurantAddress.setText(currentRest.getAddress());
        holder.restaurantPrice.setText(currentRest.getPrice());

        Glide.with(holder.itemView.getContext())
                        .load(currentRest.getImgUrl())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.hotel_ph))
                                .error(R.drawable.hotel_ph)
                        .into(holder.restImg);

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
        return restaurants.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView restaurantName;
        TextView restaurantAddress;
        TextView restaurantPrice;
        ImageView restImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.titleRestaurant);
            restaurantAddress = itemView.findViewById(R.id.addressRestaurant);
            restaurantPrice = itemView.findViewById(R.id.priceRestaurant);
            restImg = itemView.findViewById(R.id.picRestaurant);
        }
    }
}
