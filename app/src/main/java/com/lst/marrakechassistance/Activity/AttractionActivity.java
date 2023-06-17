package com.lst.marrakechassistance.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.lst.marrakechassistance.R;

public class AttractionActivity extends AppCompatActivity {
    private ShimmerFrameLayout mShimmerViewContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);
        mShimmerViewContainer = findViewById(R.id.actshimmerLayout);
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
}