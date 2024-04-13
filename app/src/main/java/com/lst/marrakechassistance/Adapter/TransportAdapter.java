package com.lst.marrakechassistance.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lst.marrakechassistance.Model.BusLine;
import com.lst.marrakechassistance.R;

import java.util.List;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.ViewHolder> {
    List<BusLine> lines;

    OnItemClickListener onItemClickListener;
    public TransportAdapter(List<BusLine> lines) {
        this.lines = lines;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_transport,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            BusLine line = lines.get(position);
            holder.lineNumber.setText(line.getLineNum());
            holder.lineStart.setText(line.getDepart());
            holder.lineEnd.setText(line.getTerminus());

        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView lineNumber, lineStart, lineEnd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lineNumber = itemView.findViewById(R.id.lineNumber);
            lineStart = itemView.findViewById(R.id.busStart);
            lineEnd = itemView.findViewById(R.id.BusTerminus);
        }
    }
}
