package com.lst.marrakechassistance.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;

public class HotelDeatilsActivity extends AppCompatActivity {
    private TextView hotelName, hotelAddress, hotelPrice, hotelDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_deatils);

        hotelName = findViewById(R.id.titleHotelDetail);

        // Get the selected hotel object from the intent
        Hotel selectedHotel = (Hotel) getIntent().getSerializableExtra("object");
        Toast.makeText(this, selectedHotel.getName(), Toast.LENGTH_SHORT).show();
        updateUI(selectedHotel);
    }

    private void updateUI(Hotel hotel){
        hotelName.setText(hotel.getName());
    }

}