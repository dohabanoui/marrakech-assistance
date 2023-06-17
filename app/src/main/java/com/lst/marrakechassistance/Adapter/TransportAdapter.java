package com.lst.marrakechassistance.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lst.marrakechassistance.Model.Transportt;
import com.lst.marrakechassistance.R;

import java.util.List;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.ViewHolder> {
    List<Transportt> transports;

    public TransportAdapter(List<Transportt> transports) {
        this.transports = transports;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_transport,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.TransportID.setText(transports.get(position).getID());
        holder.Transportligne_id.setText(transports.get(position).getLigne_id());
        holder.TransportStation.setText(transports.get(position).getStation());
        holder.Transportligne_num.setText(transports.get(position).getLigne_num());


    }

    @Override
    public int getItemCount() {
        return transports.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView TransportID;
        TextView Transportligne_id;
        TextView TransportStation;
        TextView Transportligne_num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TransportID = itemView.findViewById(R.id.IdTransport);
            Transportligne_id = itemView.findViewById(R.id.ligneidTransport);
            TransportStation = itemView.findViewById(R.id.stationTransport);
            Transportligne_num = itemView.findViewById(R.id.lignenumTransport);

        }
    }
}
