package com.lst.marrakechassistance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.Adapter.AttractionsAdapter;
import com.lst.marrakechassistance.Model.AttractionModelClass;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.AttractionsUtil;

import java.util.ArrayList;

public class ResultAttractionsActivity extends AppCompatActivity {
    private ShimmerFrameLayout mShimmerViewContainer;
    RecyclerView recyclerView;
    AttractionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reslt_attractions);
        Intent intent = getIntent();
       String query = intent.getStringExtra("query");

       mShimmerViewContainer = findViewById(R.id.shimmerLayout);
        recyclerView = findViewById(R.id.restRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new AttractionsDataLoader().execute(query);
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
}