package com.lst.marrakechassistance.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lst.marrakechassistance.Model.Restaurant;
import com.lst.marrakechassistance.R;


public class RestaurantDetailsActivity extends AppCompatActivity {
    ImageView down_arrow;
    ScrollView scrollView;
    Animation fromButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        // Get the data passed from the previous activity
        Intent intent = getIntent();
        if (intent != null) {
            Restaurant selectedRestaurant = (Restaurant) intent.getSerializableExtra("selectedRestaurant");

            // Use the selectedRestaurant object to display the details in your activity
            // For example, you can access the restaurant's name:
            String restaurantName = selectedRestaurant.getName();
            String restaurantAddress = selectedRestaurant.getAddress();
            String restaurantPrice = selectedRestaurant.getPrice();
            String restaurantPhone = selectedRestaurant.getPhone();
            String restaurantAbout = selectedRestaurant.getDescription();
            String restaurantCuisine = selectedRestaurant.getCuisine();
            String restaurantFeatures = selectedRestaurant.getFeatures();
            String restaurantMeals = selectedRestaurant.getMeals();
            String restaurantGuru_time = selectedRestaurant.getGuru_time();
            String restaurantSpecial_diets= selectedRestaurant.getSpecial_diets();
            String restaurantInfo = selectedRestaurant.getInfo();
            String restaurantNear_res = selectedRestaurant.getNear_res();
            String restaurantNear_hot = selectedRestaurant.getNear_hot();
            String restaurantNear_att = selectedRestaurant.getNear_att();
            String imgUrl = selectedRestaurant.getImgUrl();
            // Display the restaurant's name in a TextView
            TextView nameTextView = findViewById(R.id.titleRestaurantDetail);
            TextView addressTextView = findViewById(R.id.localisationRestaurant);
            TextView priceTextView = findViewById(R.id.priceRestaurant);
            TextView phoneTextView = findViewById(R.id.ContactRestaurant);
            TextView aboutTextView = findViewById(R.id.descriptionRestaurant);
            TextView cuisineTextView = findViewById(R.id.cuisineRestaurant);
            TextView featuresTextView = findViewById(R.id.featuresRestaurant);
            TextView mealsTextView = findViewById(R.id.mealsRestaurant);
            TextView guru_timeTextView = findViewById(R.id.arrivalRestaurant);
            TextView special_dietsTextView = findViewById(R.id.special_diets);
            TextView infoTextView = findViewById(R.id.infoRestaurant);
            TextView near_resTextView = findViewById(R.id.near_resRestaurant);
            TextView near_hotTextView = findViewById(R.id.near_hotRestaurant);
            TextView near_attTextView = findViewById(R.id.near_attRestaurant);

            ImageView imageView = findViewById(R.id.imgDetailRestaurant);

            nameTextView.setText(restaurantName);
            addressTextView.setText(restaurantAddress);
            priceTextView.setText(restaurantPrice);
            phoneTextView.setText(restaurantPhone);
            phoneTextView.setText(restaurantPhone);
            aboutTextView.setText(restaurantAbout);
            cuisineTextView.setText(restaurantCuisine);
            featuresTextView.setText(restaurantFeatures);
            mealsTextView.setText(restaurantMeals);
            guru_timeTextView.setText(restaurantGuru_time);
            special_dietsTextView.setText(restaurantSpecial_diets);
            infoTextView.setText(restaurantInfo);
            near_resTextView.setText(restaurantNear_res);
            near_hotTextView.setText(restaurantNear_hot);
            near_attTextView.setText(restaurantNear_att);

            Glide.with(this)
                    .load(imgUrl)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.hotel_ph)
                            .error(R.drawable.hotel_ph))
                    .into(imageView);

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
        }
        down_arrow.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api= Build.VERSION_CODES.LOLLIPOP)

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


}


