package com.lst.marrakechassistance.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.RestaurantResultAdapter;
import com.lst.marrakechassistance.Model.Restaurant;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.RestaurantUtil;

import java.util.ArrayList;

public class ResultRestaurantsActivity extends AppCompatActivity {
    private ShimmerFrameLayout mShimmerViewContainer;
    RecyclerView recyclerView;
    ArrayList<Restaurant> restaurants;
    RestaurantResultAdapter adapter;
    String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_restaurants);
        Intent intent = getIntent();
        String selectedCategory = intent.getStringExtra("category");
        String query = intent.getStringExtra("query");
        mShimmerViewContainer = findViewById(R.id.shimmerLayout);

        recyclerView = findViewById(R.id.restRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new RestaurantsDataLoader().execute(query);

    }

    // asyncTask for getting the data from the local database
    private class RestaurantsDataLoader extends AsyncTask<String , Void, ArrayList<Restaurant>> {

        @Override
        protected ArrayList<Restaurant> doInBackground(String... strings) {
            String query = strings[0];
            return (ArrayList<Restaurant>) new RestaurantUtil(ResultRestaurantsActivity.this).getRestaurants(query);
        }
        @Override
        protected void onPostExecute(ArrayList<Restaurant> result) {
            // Hide the shimmer animation
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);

            restaurants = result;
            adapter = new RestaurantResultAdapter(restaurants, ResultRestaurantsActivity.this);

            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RestaurantResultAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(ResultRestaurantsActivity.this, RestaurantDetailsActivity.class);
                    intent.putExtra("selectedRestaurant", restaurants.get(position));
                    startActivity(intent);
                }
            });
        }
    }
}