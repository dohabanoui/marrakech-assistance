package com.lst.marrakechassistance.Activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lst.marrakechassistance.R;


public class PremierePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premiere_page);

        Button beginButton = findViewById(R.id.begin);
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Démarrer l'activité MainActivity
                Intent intent = new Intent(PremierePageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
