package com.lst.marrakechassistance.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;
import com.lst.marrakechassistance.R;

public class FavoritesFragment extends Fragment {
    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ImageView imageViewNotLoggedIn = view.findViewById(R.id.imageViewNotLoggedIn);
        TextView textViewNotLoggedIn = view.findViewById(R.id.textViewNotLoggedIn);
        if (user== null) {

        } else {
            imageViewNotLoggedIn.setVisibility(View.GONE);
            textViewNotLoggedIn.setVisibility(View.GONE);
        }
        return view;
    }
}