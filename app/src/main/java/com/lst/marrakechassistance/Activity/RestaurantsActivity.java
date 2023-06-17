package com.lst.marrakechassistance.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.RestaurantAdapter;
import com.lst.marrakechassistance.Model.Restaurant;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.RestaurantUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class RestaurantsActivity extends AppCompatActivity {
    ArrayList<Restaurant> restaurants;
    RecyclerView recyclerView;
    RestaurantAdapter adapter;
    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        mShimmerViewContainer = findViewById(R.id.restshimmerLayout);

        recyclerView = findViewById(R.id.recyclerViewrestaurant);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
       // restaurants = (ArrayList<Restaurant>) new RestaurantUtil(getApplicationContext()).getAllRestaurant();
       // adapter = new RestaurantAdapter(restaurants);
        //recyclerView.setAdapter(adapter);
        new RestaurantsActivity.RestaurantsDataLoadingTask().execute();

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
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private class RestaurantsDataLoadingTask extends AsyncTask<Void, Void, ArrayList<Restaurant>> {
        @Override
        protected ArrayList<Restaurant> doInBackground(Void... params) {
            return (ArrayList<Restaurant>) new RestaurantUtil(RestaurantsActivity.this).getAllRestaurant();
        }

        @Override
        protected void onPostExecute(ArrayList<Restaurant> result) {
            // Hide the shimmer animation
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);

            // Update the RecyclerView with the retrieved hotels
            restaurants = result;
            adapter = new RestaurantAdapter(restaurants);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RestaurantAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(View view, int position) {
                    Restaurant selectedRestaurant = restaurants.get(position);

                    Intent intent = new Intent(RestaurantsActivity.this, RestaurantDetailsActivity.class);
                    intent.putExtra("selectedRestaurant", selectedRestaurant);
                    startActivity(intent);
                }
            });
        }
    }
}
