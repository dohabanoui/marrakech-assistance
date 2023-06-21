package com.lst.marrakechassistance.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;


public class HotelDetailActivity extends AppCompatActivity {
    ImageView down_arrow;
    ScrollView scrollView;
    Animation fromButton;
    ImageView hotelImg;
    TextView hotelTitle, hotelPrice, hotelAddress, hotelPhone, hotelType, hotelDescription, hotelStyle, hotelProp, hotelLang,hotelNearRest, hotelNearAttr, hotelInfo, hotelEval;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_details);
        hotelImg = findViewById(R.id.imgDetailHotel);
        hotelTitle = findViewById(R.id.titleHotelDetail);
        hotelPrice = findViewById(R.id.priceHotel);
        hotelAddress = findViewById(R.id.localisationHotel);
        hotelPhone = findViewById(R.id.ContactHotel);
        hotelType = findViewById(R.id.typeHotel);
        hotelDescription = findViewById(R.id.descriptionHotel);
        hotelStyle = findViewById(R.id.styleHotel);
        hotelProp = findViewById(R.id.PropertiesHotel);
        hotelLang = findViewById(R.id.languagesHotel);
        hotelNearRest = findViewById(R.id.near_resHotel);
        hotelNearAttr = findViewById(R.id.near_attHotel);
        hotelInfo = findViewById(R.id.infoHotel);
        hotelEval = findViewById(R.id.StarsHotel);


        Intent intent = getIntent();
        if (intent != null) {
            Hotel selectedHotel = (Hotel) intent.getSerializableExtra("selectedHotel");


            down_arrow = findViewById(R.id.down_arrow);
            scrollView = findViewById(R.id.scrollView);

            fromButton = AnimationUtils.loadAnimation(this,R.anim.anim_from_bottom);
            down_arrow.setAnimation(fromButton);
            scrollView.setAnimation(fromButton);

            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );

            this.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            |View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            |View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            |View.SYSTEM_UI_FLAG_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );


            Glide.with(this)
                    .load(selectedHotel.getImgUrl())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.hotel_ph)
                            .error(R.drawable.hotel_ph))
                    .into(hotelImg);
            hotelTitle.setText(selectedHotel.getName());
            hotelPrice.setText(selectedHotel.getPrice());
            hotelAddress.setText(selectedHotel.getAddress());
            hotelPhone.setText(selectedHotel.getPhone());
            hotelType.setText(selectedHotel.getType());
            hotelDescription.setText(selectedHotel.getDescription());
            hotelStyle.setText(selectedHotel.getStyles());
            hotelProp.setText(selectedHotel.getProperties());
            hotelLang.setText(selectedHotel.getLanguages());
            hotelNearRest.setText(selectedHotel.getNear_res());
            hotelNearAttr.setText(selectedHotel.getNear_att());
            hotelInfo.setText(selectedHotel.getInfo());
            hotelEval.setText(selectedHotel.getStars());

        }
        down_arrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
    }


}


