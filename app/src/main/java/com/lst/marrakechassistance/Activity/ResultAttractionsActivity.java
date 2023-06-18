package com.lst.marrakechassistance.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.AttractionsAdapter;
import com.lst.marrakechassistance.Model.AttractionModelClass;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.AppReference;
import com.lst.marrakechassistance.utils.AttractionsUtil;

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

public class ResultAttractionsActivity extends AppCompatActivity {
    private ShimmerFrameLayout mShimmerViewContainer;
    RecyclerView recyclerView;
    AttractionsAdapter adapter;
    String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reslt_attractions);
        Intent intent = getIntent();
        String selectedCategory = intent.getStringExtra("category");
        String query = intent.getStringExtra("query");

       mShimmerViewContainer = findViewById(R.id.shimmerLayout);
       recyclerView = findViewById(R.id.restRecyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       if (isConnectedToInternet()){
           ipAddress = new AppReference(this).getIpAddress();
           assert ipAddress != null;
           Toast.makeText(this, "Connected To internet", Toast.LENGTH_SHORT).show();

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
                   e.printStackTrace();
                   new AttractionsDataLoader().execute(query);
               }

               @Override
               public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                   assert response.body() != null;
                   String responseData = response.body().string();
                   try {
                       JSONArray json = new JSONArray(responseData);
                       ArrayList<AttractionModelClass> tempAttractions = new ArrayList<AttractionModelClass>();
                       for (int i = 0; i < json.length(); i++) {
                           JSONObject jsonObject = json.getJSONObject(i);
                           AttractionModelClass attraction = new AttractionModelClass();
                            attraction.setName(jsonObject.getString("Names"));
                            attraction.setAdress(jsonObject.getString("adress"));
                            attraction.setDescription(jsonObject.getString("description"));
                            attraction.setWebsite(jsonObject.getString("WebSite"));
                            attraction.setSug_dur(jsonObject.getString("Suggested duration"));
                            attraction.setOpen_dur(jsonObject.getString("open during"));
                            attraction.setCategory(jsonObject.getString("category"));
                            attraction.setNear_res(jsonObject.getString("near_res"));
                            attraction.setNear_att(jsonObject.getString("near_att"));
                            attraction.setGps(jsonObject.getString("gps"));
                            attraction.setImg(jsonObject.getString("img_url"));
                            tempAttractions.add(attraction);
                       }
                       runOnUiThread(
                               () -> {
                                   mShimmerViewContainer.stopShimmer();
                                   mShimmerViewContainer.setVisibility(View.GONE);
                                   adapter = new AttractionsAdapter(tempAttractions, ResultAttractionsActivity.this);
                                   recyclerView.setAdapter(adapter);
                               }
                       );

                   } catch (JSONException e){
                       e.printStackTrace();
                   }

               }
           });

       } else {
           new AttractionsDataLoader().execute(query);
       }
    }

    private class AttractionsDataLoader extends AsyncTask<String , Void, ArrayList<AttractionModelClass>>{

        @Override
        protected ArrayList<AttractionModelClass> doInBackground(String... strings) {
            String query = strings[0];
            return (ArrayList<AttractionModelClass>) new AttractionsUtil(ResultAttractionsActivity.this).getAttractions(query);
        }

        @Override
        protected void onPostExecute(ArrayList<AttractionModelClass> result) {
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);

            adapter = new AttractionsAdapter(result, ResultAttractionsActivity.this);
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