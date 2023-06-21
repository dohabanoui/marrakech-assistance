package com.lst.marrakechassistance.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.Model.Place;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.PlaceUtil;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    List<Hotel> hotels;
    PlaceUtil util;
    private OnItemClickListener onItemClickListener;

    public HotelAdapter(List<Hotel> hotels, PlaceUtil util) {
        this.hotels = hotels;
        this.util = util;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_hotel,parent,false);
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
        holder.hotelName.setText(hotels.get(position).getName());
        holder.hotelAddress.setText(hotels.get(position).getAddress());
        holder.hotelPrice.setText(hotels.get(position).getPrice());

        if (hotels.get(position).getFavorite()){
            holder.favIcon.setImageResource(R.drawable.round_filled_favorite_24);
        }
        // Load the image
        Glide.with(holder.itemView.getContext())
                        .load(hotels.get(position).getImgUrl())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.hotel_ph)
                                .error(R.drawable.hotel_ph)
                        )
                        .into(holder.hotelImg);

        // set The rating
        if (hotels.get(position).getStars().equals("unclassified")){
            holder.hotelRatingBar.setVisibility(View.GONE);
        } else {
            holder.hotelRatingBar.setRating(Float.parseFloat(hotels.get(position).getStars()));
        }

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView hotelName;
        TextView hotelAddress;
        TextView hotelPrice;
        ImageView hotelImg;
        RatingBar hotelRatingBar;
        ImageView favIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.titleHotel);
            hotelAddress = itemView.findViewById(R.id.addressHotel);
            hotelPrice = itemView.findViewById(R.id.priceHotel);
            hotelImg = itemView.findViewById(R.id.picHotel);
            hotelRatingBar = itemView.findViewById(R.id.ratingBarHotel);
            favIcon = itemView.findViewById(R.id.favoriteIcon);
            favIcon.setOnClickListener(view -> {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    Place place = hotels.get(position);
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
