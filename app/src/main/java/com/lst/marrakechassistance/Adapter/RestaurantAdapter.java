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
import com.lst.marrakechassistance.Model.Place;
import com.lst.marrakechassistance.Model.Restaurant;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.PlaceUtil;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    List<Restaurant> restaurants;
    private OnItemClickListener onItemClickListener;
    PlaceUtil util;

    public RestaurantAdapter(List<Restaurant> restaurants, PlaceUtil util) {
        this.restaurants = restaurants;
        this.util = util;
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
        if(restaurants.get(position).getFavorite()){
            holder.favIcon.setImageResource(R.drawable.round_filled_favorite_24);
        }

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

        ImageView favIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.titleRestaurant);
            restaurantAddress = itemView.findViewById(R.id.addressRestaurant);
            restaurantPrice = itemView.findViewById(R.id.priceRestaurant);
            restImg = itemView.findViewById(R.id.picRestaurant);
            favIcon = itemView.findViewById(R.id.favoriteIcon);
            favIcon.setOnClickListener(view -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Place place = restaurants.get(position);
                    if (place.getFavorite()){
                        util.removeFavorite(place);
                    } else {
                        util.setFavorite(place);
                    }
                    place.setFavorite(!place.getFavorite());
                    notifyItemChanged(position);
                }
            });
        }
    }
}
