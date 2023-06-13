package com.lst.marrakechassistance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lst.marrakechassistance.Fragment.SearchActivitiesFragment;
import com.lst.marrakechassistance.Fragment.SearchCategoriesFragment;
import com.lst.marrakechassistance.Fragment.SearchHotelsFragment;
import com.lst.marrakechassistance.Fragment.SearchRestaurantsFragment;
import com.lst.marrakechassistance.R;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements SearchCategoriesFragment.OnCategorySelectedListener {
    private FrameLayout fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        fragmentContainer = findViewById(R.id.fragment_container);

        // show category fragment initially
        showCategoryFragment();
    }

    private void showCategoryFragment() {
        SearchCategoriesFragment categoryFragment = new SearchCategoriesFragment();
        replaceFragment(categoryFragment);
    }

    @Override
    public void onCategorySelected(String category) {
        // Handle category selection and replace the fragment
        if (Objects.equals(category, "hotels")){
            SearchHotelsFragment searchHotelsFragment = new SearchHotelsFragment();
            replaceFragment(searchHotelsFragment);
        } else if (Objects.equals(category, "restaurants")){
            SearchRestaurantsFragment fragment = new SearchRestaurantsFragment();
            replaceFragment(fragment);
        } else if (Objects.equals(category, "transport")) {
            Intent intent = new Intent(this, TransportsActivity.class);
            startActivity(intent);

        } else if (Objects.equals(category, "activities")) {
            SearchActivitiesFragment activityFragment = new SearchActivitiesFragment();
            replaceFragment(activityFragment);
        }
    }

    private void replaceFragment(Fragment fragment) {

        // replace the fragment container with the new fragment

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(fragmentContainer.getId(), fragment);
        transaction.commit();
    }
}