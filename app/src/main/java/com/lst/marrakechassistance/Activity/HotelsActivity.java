package com.lst.marrakechassistance.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.HotelAdapter;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.HotelUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

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

        recyclerView = findViewById(R.id.recyclerViewhotel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mShimmerViewContainer.startShimmer();

        // Perform the data retrieval operation asynchronously
        new HotelDataLoadingTask().execute();
    }


    private class HotelDataLoadingTask extends AsyncTask<Void, Void, ArrayList<Hotel>> {
        @Override
        protected ArrayList<Hotel> doInBackground(Void... params) {
            return (ArrayList<Hotel>) new HotelUtil(HotelsActivity.this).getAllHotel();
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
            adapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(View view, int position) {
                    Hotel selectedHotel = hotels.get(position);

                    Intent intent = new Intent(HotelsActivity.this, HotelDetailActivity.class);
                    intent.putExtra("selectedHotel", selectedHotel);
                    startActivity(intent);
                }
            });
        }
    }
}