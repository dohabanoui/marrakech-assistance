package com.lst.marrakechassistance.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    List<Hotel> hotels;

    public HotelAdapter(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hotelName.setText(hotels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView hotelName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hotelName = itemView.findViewById(R.id.titleTxt);
        }
    }
}
