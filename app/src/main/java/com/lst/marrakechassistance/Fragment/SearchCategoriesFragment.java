package com.lst.marrakechassistance.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lst.marrakechassistance.R;

public class SearchCategoriesFragment extends Fragment {
    private OnCategorySelectedListener categoryListener;
    LinearLayout hotelsFrag, restaurantsFrag, activitiesFrag;

    public interface OnCategorySelectedListener {
        void onCategorySelected(String category);
    }
    public SearchCategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_categories, container, false);
        hotelsFrag = view.findViewById(R.id.FragHotel);
        restaurantsFrag = view.findViewById(R.id.FragRest);
        activitiesFrag = view.findViewById(R.id.FragMV);

        hotelsFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryListener.onCategorySelected("hotels");
            }
        });
        restaurantsFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryListener.onCategorySelected("restaurants");
            }
        });
        activitiesFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryListener.onCategorySelected("activities");
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
            try {
                categoryListener = (OnCategorySelectedListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + " must implement OnCategorySelectedListener");
            }
    }
}