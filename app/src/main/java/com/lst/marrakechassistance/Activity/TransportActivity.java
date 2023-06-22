package com.lst.marrakechassistance.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.TransportAdapter;
import com.lst.marrakechassistance.Model.BusLine;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.TransportUtil;

import java.util.ArrayList;

public class TransportActivity extends AppCompatActivity {

    private ShimmerFrameLayout mShimmerViewContainer;
    RecyclerView recyclerView;
    TransportAdapter adapter;
    ArrayList<BusLine> lines;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        mShimmerViewContainer = findViewById(R.id.shimmerLayout);

        recyclerView = findViewById(R.id.recyclerViewtransport);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mShimmerViewContainer.startShimmer();

        // Perform the data retrieval operation asynchronously
        new TransportDataLoadingTask().execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer();
        super.onPause();
    }
    private class TransportDataLoadingTask extends AsyncTask<Void, Void, ArrayList<BusLine>> {
        @Override
        protected ArrayList<BusLine> doInBackground(Void... params) {
            return (ArrayList<BusLine>) new TransportUtil(TransportActivity.this).getAllTransport();
        }

        @Override
        protected void onPostExecute(ArrayList<BusLine> result) {
            // Hide the shimmer animation
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);

            lines = result;
            adapter = new TransportAdapter(lines);
            adapter.setOnItemClickListener(new TransportAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(TransportActivity.this, LineDetailsActivity.class);
                    intent.putExtra("selectedLine", lines.get(position));
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
}}}