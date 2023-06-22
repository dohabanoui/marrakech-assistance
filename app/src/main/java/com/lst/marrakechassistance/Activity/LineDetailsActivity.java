package com.lst.marrakechassistance.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lst.marrakechassistance.Adapter.StationAdapter;
import com.lst.marrakechassistance.Model.BusLine;
import com.lst.marrakechassistance.Model.Station;
import com.lst.marrakechassistance.R;
import com.lst.marrakechassistance.utils.TransportUtil;

import java.util.ArrayList;

public class LineDetailsActivity extends AppCompatActivity {

    TextView lineNumber, lineStart, lineEnd;
    RecyclerView recyclerView;
    StationAdapter adapter;
    ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_details);
        Intent intent = getIntent();
        BusLine selectedLine = (BusLine) intent.getSerializableExtra("selectedLine");
        backBtn = findViewById(R.id.backBtn);
        recyclerView  =findViewById(R.id.stationsRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (selectedLine !=null){
            lineNumber = findViewById(R.id.lineNumber);
            lineStart = findViewById(R.id.lineDep);
            lineEnd = findViewById(R.id.lineEnd);

            lineNumber.setText(selectedLine.getLineNum());
            lineStart.setText(selectedLine.getDepart());
            lineEnd.setText(selectedLine.getTerminus());
            new getStationsTask().execute(selectedLine.getId());
        }

        backBtn.setOnClickListener(view->onBackPressed());
    }

    private class getStationsTask extends AsyncTask<String, Void, ArrayList<Station>> {

        @Override
        protected ArrayList<Station> doInBackground(String... ints) {
            String id = ints[0];
            return (ArrayList<Station>) new TransportUtil(LineDetailsActivity.this).getStationByID(id);
        }
        @Override
        protected void onPostExecute(ArrayList<Station> stations) {
            adapter = new StationAdapter(stations);
            recyclerView.setAdapter(adapter);
        }
    }
}