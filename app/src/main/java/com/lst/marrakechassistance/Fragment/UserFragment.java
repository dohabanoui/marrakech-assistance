package com.lst.marrakechassistance.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lst.marrakechassistance.Activity.LoginActivity;
import com.lst.marrakechassistance.R;

public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_user, container, false);

        Button btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {

                // Handle button click, navigate to login activity
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

        });
        return view;
    }
}