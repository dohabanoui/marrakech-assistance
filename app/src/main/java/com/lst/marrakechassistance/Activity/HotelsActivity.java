package com.lst.marrakechassistance.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.HotelAdapter;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.HotelUtil;

import java.util.ArrayList;

public class HotelsActivity extends AppCompatActivity {

    private ShimmerFrameLayout mShimmerViewContainer;
    RecyclerView recyclerView;
    HotelAdapter adapter;
    ArrayList<Hotel> hotels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        mShimmerViewContainer = findViewById(R.id.shimmerLayout);

        recyclerView = findViewById(R.id.hotelsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShimmerViewContainer.startShimmer();

        // Perform the data retrieval operation asynchronously
        new HotelDataLoadingTask().execute();
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
    private class HotelDataLoadingTask extends AsyncTask<Void, Void, ArrayList<Hotel>> {
        @Override
        protected ArrayList<Hotel> doInBackground(Void... params) {
            return (ArrayList<Hotel>) new HotelUtil(HotelsActivity.this).getAllHotels();
        }

        @Override
        protected void onPostExecute(ArrayList<Hotel> result) {
            // Hide the shimmer animation
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);

            // Update the RecyclerView with the retrieved hotels
            hotels = result;
            adapter = new HotelAdapter(hotels);
            recyclerView.setAdapter(adapter);
        }
    }
}