package com.lst.marrakechassistance.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lst.marrakechassistance.Model.Attraction;
import com.lst.marrakechassistance.R;

public class AttractionDetailsActivity extends AppCompatActivity {
    ImageView down_arrow;
    ScrollView scrollView;
    Animation fromButton;
    ImageView image;
    TextView titleTv,mailTv ,addressTv, categoryTv, descriptionTv,openingHoursTv, nearAttr, NearRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_details);

        Intent intent = getIntent();
        Attraction selectedAttraction = (Attraction) intent.getSerializableExtra("selectedAttr");
        if (selectedAttraction != null){
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


            image = (ImageView) findViewById(R.id.imgDetailAttr);
            titleTv = (TextView) findViewById(R.id.titleAttr);
            addressTv = (TextView) findViewById(R.id.localisationAttr);
            categoryTv = (TextView) findViewById(R.id.attrType);
            descriptionTv = (TextView) findViewById(R.id.descriptionAttr);
            openingHoursTv = (TextView) findViewById(R.id.openingHours);
            nearAttr = (TextView) findViewById(R.id.near_attHotel);
            NearRes = (TextView) findViewById(R.id.near_resHotel);

            Glide.with(this).load(selectedAttraction.getImgUrl())
                    .apply(new RequestOptions()
                            .error(R.drawable.hotel_ph)
                            .placeholder(R.drawable.hotel_ph))
                    .into(image);
            titleTv.setText(selectedAttraction.getName());
            addressTv.setText(selectedAttraction.getAddress());
            categoryTv.setText(selectedAttraction.getCategory());
            descriptionTv.setText(selectedAttraction.getDescription());
            openingHoursTv.setText(selectedAttraction.getOpen_dur());
            nearAttr.setText(selectedAttraction.getNear_att());
            NearRes.setText(selectedAttraction.getNear_res());
        }
        down_arrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}