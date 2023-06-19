package com.lst.marrakechassistance.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.RestaurantResultAdapter;
import com.lst.marrakechassistance.Model.Restaurant;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.AppReference;
import com.lst.marrakechassistance.utils.RestaurantUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

        // check if the user is connected or not
        // if he's connected then send the request to the server
        // the difference between the two mode is on online mode
        // we're using faiss with Sbert for Semantic Search
        if (isConnectedToInternet()) {
           // get The ip Address
            ipAddress = new AppReference(this).getIpAddress();
            assert ipAddress != null;

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS) // Set read timeout
                    .writeTimeout(10, TimeUnit.SECONDS) // Set write timeout
                    .build();
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("category", selectedCategory)
                    .addFormDataPart("query",query)
                    .build();

            Request request = new Request.Builder()
                    .url("http://" + ipAddress + ":5000/search")
                    .post(requestBody)
                    .build();

            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    // if the result failed load the data from the local
                    e.printStackTrace();
                    new RestaurantsDataLoader().execute(query);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    // if the server response with JsonData
                    String responseData = response.body().string();
                    try {
                        JSONArray jsonData = new JSONArray(responseData);
                        ArrayList<Restaurant> temRestaurants = new ArrayList<>();

                        for (int i = 0; i < jsonData.length(); i++) {
                            JSONObject jsonObject = jsonData.getJSONObject(i);
                            Restaurant restaurant = new Restaurant();
                            restaurant.setName(jsonObject.getString("Names"));
                            restaurant.setAddress(jsonObject.getString("ad_adress"));
                            restaurant.setDescription(jsonObject.getString("ad_about"));
                            restaurant.setPhone(jsonObject.getString("ad_Phone"));
                            restaurant.setWebsite(jsonObject.getString("ad_WebSite"));
                            restaurant.setCuisine(jsonObject.getString("ad_cuisine"));
                            restaurant.setFeatures(jsonObject.getString("ad_features"));
                            restaurant.setMeals(jsonObject.getString("ad_meals"));
                            restaurant.setPrice(jsonObject.getString("ad_prices"));
                            restaurant.setNear_att(jsonObject.getString("near_att"));
                            restaurant.setNear_hot(jsonObject.getString("near_hot"));
                            restaurant.setSpecial_diets(jsonObject.getString("special_diets"));
                            restaurant.setImgUrl(jsonObject.getString("img_url"));
                            temRestaurants.add(restaurant);
                        }
                        runOnUiThread(()->{
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            adapter = new RestaurantResultAdapter(temRestaurants, ResultRestaurantsActivity.this);
                            recyclerView.setAdapter(adapter);
                        });

                      adapter.setOnItemClickListener(new RestaurantResultAdapter.OnItemClickListener() {
                          @Override
                          public void onItemClick(View view, int position) {
                              Intent intent = new Intent(ResultRestaurantsActivity.this, RestaurantDetailsActivity.class);
                              intent.putExtra("selectedRestaurant", restaurants.get(position));
                              startActivity(intent);
                          }
                      });

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
            // if the user is not connected he can still fetch the data from the local
            // in this mode we're using chaquopy with rank_bm25
            new RestaurantsDataLoader().execute(query);
        }
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
    private boolean isConnectedToInternet() {
        // Check The connectivity of The user
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}