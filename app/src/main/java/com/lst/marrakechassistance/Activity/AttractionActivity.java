package com.lst.marrakechassistance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.AttractionsAdapter;
import com.lst.marrakechassistance.Model.Attraction;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.AttractionsUtil;
import com.lst.marrakechassistance.utils.PlaceUtil;

import java.util.ArrayList;

public class AttractionActivity extends AppCompatActivity {
    private ShimmerFrameLayout mShimmerViewContainer;

    private RecyclerView recyclerView;
    private AttractionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);
        mShimmerViewContainer = findViewById(R.id.actshimmerLayout);
        recyclerView = findViewById(R.id.attractionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new AttractionDataLoader().execute();
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



    private class AttractionDataLoader extends AsyncTask<Void, Void, ArrayList<Attraction>>{

        @Override
        protected ArrayList<Attraction> doInBackground(Void... voids) {
            return (ArrayList<Attraction>) new AttractionsUtil(AttractionActivity.this).getAllAttractions();
        }

        @Override
        protected void onPostExecute(ArrayList<Attraction> attractions) {
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);
            Toast.makeText(AttractionActivity.this, "data fetched", Toast.LENGTH_SHORT).show();
            adapter = new AttractionsAdapter(attractions,AttractionActivity.this, new PlaceUtil(getApplicationContext()));
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new AttractionsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(AttractionActivity.this,AttractionDetailsActivity.class);
                    intent.putExtra("selectedAttr",attractions.get(position));
                    startActivity(intent);
                }
            });
        }
    }
}