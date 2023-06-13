package com.lst.marrakechassistance.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lst.marrakechassistance.R;

import java.util.List;

public class CommonQueryAdapter extends RecyclerView.Adapter<CommonQueryAdapter.CommonQueryHolder> {
    private List<String> queryList;

    public CommonQueryAdapter(List<String> queryList) {
        this.queryList = queryList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setQueryList(List<String> queryList) {
        this.queryList = queryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommonQueryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_common_query, parent, false);
        return new CommonQueryHolder(view);
    }

    @Override
    public int getItemCount() {
        return queryList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CommonQueryHolder holder, int position) {
        String query = queryList.get(position);
        holder.setQuery(query);
    }

    class CommonQueryHolder extends RecyclerView.ViewHolder{
        private TextView query;
        public CommonQueryHolder(View itemView) {
            super(itemView);
            query = itemView.findViewById(R.id.edit_query);
        }

        public void setQuery(String query){
            this.query.setText(query);
        }
    }
}
