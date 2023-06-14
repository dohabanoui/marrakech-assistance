package com.lst.marrakechassistance.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.HotelResultAdapter;
import com.lst.marrakechassistance.Model.HotelModelClass;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.HotelUtil;

import java.util.ArrayList;

public class ResultsHotelsActivity extends AppCompatActivity {
    private ShimmerFrameLayout mShimmerViewContainer;
    RecyclerView recyclerView;
    ArrayList<HotelModelClass> hotels;
    HotelResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Intent intent = getIntent();
        String selectedCategory = intent.getStringExtra("category");
        String query = intent.getStringExtra("query");
        mShimmerViewContainer = findViewById(R.id.shimmerLayout);

        recyclerView = findViewById(R.id.hotelsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Perform the data retrieval operation asynchronously
        new ResultsHotelsActivity.HotelDataLoadingTask().execute(query);

    }
    private class HotelDataLoadingTask extends AsyncTask<String, Void, ArrayList<HotelModelClass>> {
        @Override
        protected ArrayList<HotelModelClass> doInBackground(String... params) {
            String query = params[0];
            return (ArrayList<HotelModelClass>) new HotelUtil(ResultsHotelsActivity.this).getHotels(query);
        }
        @Override
        protected void onPostExecute(ArrayList<HotelModelClass> result) {
            // Hide the shimmer animation
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);

            // Update the RecyclerView with the retrieved hotels
            hotels = result;
            adapter = new HotelResultAdapter(hotels);
            recyclerView.setAdapter(adapter);
        }
    }
}