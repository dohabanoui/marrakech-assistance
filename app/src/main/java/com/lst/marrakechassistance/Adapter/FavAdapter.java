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
import com.lst.marrakechassistance.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    ArrayList<Place> places;

    public FavAdapter(ArrayList<Place> places) {
        this.places = places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place place = places.get(position);
        holder.mTitle.setText(place.getName());
        holder.mDescription.setText(place.getDescription());
        holder.mType.setText(place.getPlaceType());
        Glide.with(holder.itemView.getContext())
                .load(place.getImgUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.hotel_ph)
                        .error(R.drawable.hotel_ph))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return places.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;
        TextView mTitle, mDescription, mType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.favoriteImage);
            mTitle = itemView.findViewById(R.id.favoriteTitle);
            mDescription = itemView.findViewById(R.id.favoriteDescription);
            mType = itemView.findViewById(R.id.favoriteType);
        }
    }
}
