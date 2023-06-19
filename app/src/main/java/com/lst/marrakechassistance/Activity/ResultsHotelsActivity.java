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
import com.lst.marrakechassistance.Adapter.HotelAdapter;
import com.lst.marrakechassistance.Adapter.HotelResultAdapter;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.AppReference;
import com.lst.marrakechassistance.utils.HotelUtil;

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

public class ResultsHotelsActivity extends AppCompatActivity {
    private ShimmerFrameLayout mShimmerViewContainer;
    RecyclerView recyclerView;
    ArrayList<Hotel> hotels;
    HotelResultAdapter adapter;
    String ipAddress;

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
        // check if the user is connected to Internet
        if (isConnectedToInternet()) {
            ipAddress = new AppReference(this).getIpAddress();
            assert ipAddress != null;
           // Toast.makeText(this, "Connected To internet", Toast.LENGTH_SHORT).show();
            // The user is connected Fetch The data from The API
            OkHttpClient client = new OkHttpClient.Builder()
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

            // send the request to the server
            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    assert response.body() != null;
                    String responseData = response.body().string();
                    try {
                        JSONArray json = new JSONArray(responseData);
                        ArrayList<Hotel> tempHotels = new ArrayList<>();
                        for (int i = 0; i < json.length(); i++) {
                            JSONObject jsonObject = json.getJSONObject(i);
                            Hotel hotel = new Hotel();

                            hotel.setName(jsonObject.getString("Names"));
                            hotel.setType(jsonObject.getString("Type"));
                            hotel.setStars(jsonObject.getString("Stars"));
                            hotel.setDescription(jsonObject.getString("Description"));
                            hotel.setProperties(jsonObject.getString("Properties"));
                            hotel.setAddress(jsonObject.getString("address"));
                            hotel.setPhone(jsonObject.getString("Tel"));
                            hotel.setWebsite(jsonObject.getString("website"));
                            hotel.setNear_res(jsonObject.getString("near_res"));
                            hotel.setNear_att(jsonObject.getString("near_att"));
                            hotel.setImgUrl(jsonObject.getString("img_url"));
                            tempHotels.add(hotel);
                        }
                        // update the UI with the fetched data
                        runOnUiThread(() -> {
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);

                            hotels = tempHotels;
                            adapter = new HotelResultAdapter(hotels);
                            adapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {

                                @Override
                                public void onItemClick(View view, int position) {
                                    Hotel selectedHotel = hotels.get(position);

                                    Intent intent = new Intent(ResultsHotelsActivity.this, HotelDetailActivity.class);
                                    intent.putExtra("selectedHotel", selectedHotel);
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(adapter);
                        });

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    // if the data is note fetched from the server we can perform the fetch locally
                    new ResultsHotelsActivity.HotelDataLoadingTask().execute(query);
                    e.printStackTrace();
                }
            });
        } else {
            new ResultsHotelsActivity.HotelDataLoadingTask().execute(query);
        }
    }
    private class HotelDataLoadingTask extends AsyncTask<String, Void, ArrayList<Hotel>> {
        @Override
        protected ArrayList<Hotel> doInBackground(String... params) {
            String query = params[0];
            return (ArrayList<Hotel>) new HotelUtil(ResultsHotelsActivity.this).getHotels(query);
        }
        @Override
        protected void onPostExecute(ArrayList<Hotel> result) {
            // Hide the shimmer animation
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);

            // Update the RecyclerView with the retrieved hotels
            hotels = result;
            adapter = new HotelResultAdapter(hotels);
            adapter.setOnItemClickListener(new HotelAdapter.OnItemClickListener() {

                @Override
                public void onItemClick(View view, int position) {
                    Hotel selectedHotel = hotels.get(position);

                    Intent intent = new Intent(ResultsHotelsActivity.this, HotelDetailActivity.class);
                    intent.putExtra("selectedHotel", selectedHotel);
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapter);
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