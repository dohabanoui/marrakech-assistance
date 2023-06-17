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
import com.lst.marrakechassistance.Model.HotelModelClass;
import com.lst.marrakechassistance.R;

import java.util.List;

public class HotelResultAdapter extends RecyclerView.Adapter<HotelResultAdapter.HotelResultHolder>{
    List<HotelModelClass> hotels;

    public HotelResultAdapter(List<HotelModelClass> hotels) {
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public HotelResultAdapter.HotelResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_result_view, parent,false);
        return new HotelResultHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelResultAdapter.HotelResultHolder holder, int position) {
        HotelModelClass hotel = hotels.get(position);
        holder.name.setText(hotel.getName());
        holder.type.setText(hotel.getType());

        Glide.with(holder.itemView.getContext())
                .load(hotel.getImg())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.hotel_ph)
                        .error(R.drawable.hotel_ph))
                .into(holder.img);

        if (hotel.getStars().equals("unclassified")){
            holder.rate.setVisibility(View.GONE);
        } else {
            holder.rate.setRating(Float.parseFloat(hotel.getStars()));
        }
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public class HotelResultHolder extends RecyclerView.ViewHolder {
        TextView name ;
        TextView type ;
        ImageView img ;
        RatingBar rate;

        public HotelResultHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.hot_name1);
            this.type = itemView.findViewById(R.id.type1);
            this.img = itemView.findViewById(R.id.hotel_img);
            this.rate = itemView.findViewById(R.id.ratingBar1);
        }
    }
}
