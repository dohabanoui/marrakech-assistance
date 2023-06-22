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
import com.lst.marrakechassistance.Model.Attraction;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.AppReference;
import com.lst.marrakechassistance.utils.AttractionsUtil;
import com.lst.marrakechassistance.utils.PlaceUtil;

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

       new AttractionsDataLoader().execute(query);
    }

    private class AttractionsDataLoader extends AsyncTask<String , Void, ArrayList<Attraction>>{

        @Override
        protected ArrayList<Attraction> doInBackground(String... strings) {
            String query = strings[0];
            return (ArrayList<Attraction>) new AttractionsUtil(ResultAttractionsActivity.this).getAttractions(query);
        }

        @Override
        protected void onPostExecute(ArrayList<Attraction> result) {
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);

            adapter = new AttractionsAdapter(result, ResultAttractionsActivity.this, new PlaceUtil(ResultAttractionsActivity.this));
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new AttractionsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(ResultAttractionsActivity.this,AttractionDetailsActivity.class);
                    intent.putExtra("selectedAttr",result.get(position));
                    startActivity(intent);
                }
            });
        }
    }
}