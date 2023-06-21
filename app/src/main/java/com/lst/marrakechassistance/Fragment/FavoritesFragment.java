package com.lst.marrakechassistance.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;
import com.lst.marrakechassistance.Adapter.FavAdapter;
import com.lst.marrakechassistance.Model.Place;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.PlaceUtil;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class FavoritesFragment extends Fragment {
    LinearLayout notFound;
    RecyclerView recyclerView;
    FavAdapter adapter;
    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        notFound = (LinearLayout) view.findViewById(R.id.notFound);
        recyclerView = view.findViewById(R.id.favRecycler);
        new getFavData().execute();
        return view;
    }

    private class getFavData extends AsyncTask<Void, Void, ArrayList<Place>>{

        @Override
        protected ArrayList<Place> doInBackground(Void... voids) {
            return (ArrayList<Place>) new PlaceUtil(getContext()).getFavPlaces();
        }

        @Override
        protected void onPostExecute(ArrayList<Place> places) {
            if (places.size() == 0) {
                Log.e("size", String.valueOf(places.size()));
                notFound.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                notFound.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new FavAdapter(places);
                recyclerView.setAdapter(adapter);
            }
        }
    }
}