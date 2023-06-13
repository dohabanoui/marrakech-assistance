package com.lst.marrakechassistance.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.lst.marrakechassistance.Activity.HotelsActivity;
import com.lst.marrakechassistance.Activity.MustVisitActivity;
import com.lst.marrakechassistance.Activity.RestaurantsActivity;
import com.lst.marrakechassistance.Activity.SearchActivity;
import com.lst.marrakechassistance.Model.Hotel;
import com.lst.marrakechassistance.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment# newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ArrayList<Hotel> hotels;


    // define the variable for the weather Section
    private ImageView weatherIconImageView;
    private TextView weatherDescriptionTextView;
    private TextView weatherTemperatureTextView;

    // To handle the location
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location userLocation;



    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        // define The search Bar
        // We're not doing the search here so for that We will Make A new SearchActivity
        EditText searchBar = rootView.findViewById(R.id.recherche);
        searchBar.setFocusable(false);
        searchBar.setClickable(true);
        searchBar.setOnClickListener((view)->{
                    Intent intent = new Intent(getContext(), SearchActivity.class);
                    startActivity(intent);
                }
        );


        // Initialise The gridLayout item
        // Make a GridLayout item to reference the View GridLayout
        GridLayout gridLayout = rootView.findViewById(R.id.gridLayout);

        // set The listener for each item in the grid Layout
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View childView = gridLayout.getChildAt(i);
            childView.setOnClickListener((view)->{
                int viewId = view.getId();
                if (viewId == R.id.FragHotel){
                    Intent intent = new Intent(getContext(), HotelsActivity.class);
                    startActivity(intent);
                } else if (viewId == R.id.FragRest){
                    Intent intent = new Intent(getContext(), RestaurantsActivity.class);
                    startActivity(intent);
                } else if (viewId == R.id.FragTransport) {
                    Intent intent = new Intent(getContext(), RestaurantsActivity.class);
                    startActivity(intent);
                } else if (viewId == R.id.FragMV) {
                    Intent intent = new Intent(getContext(), MustVisitActivity.class);
                    startActivity(intent);
                }
            });
        }

        // Define The WHeather Data Elements
        weatherIconImageView = rootView.findViewById(R.id.weatherIconImageView);
        weatherDescriptionTextView = rootView.findViewById(R.id.weatherDescriptionTextView);
        weatherTemperatureTextView = rootView.findViewById(R.id.weatherTemperatureTextView);

        // Check for internet connectivity
        if (isConnectedToInternet()) {
            // If connected, make the API call to retrieve weather data
            Toast.makeText(getActivity(), "internet connection Established", Toast.LENGTH_SHORT).show();
            String API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=31.63&lon=-8.04&appid=0397f0fbb004327b9d5237dac23977b9";
            new FetchWeatherDataTask().execute(API_URL);

        } else {
            // If not connected, display a message and icon indicating no internet connection
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_SHORT).show();
            weatherIconImageView.setImageResource(R.drawable.icon_no_connection);
            weatherDescriptionTextView.setText(R.string.no_internet_connection);
            weatherTemperatureTextView.setText("");
        }

        // Get the user Current Location
        // Request location updates
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLocation = location;
                // Calculate distances to hotels
                //calculateDistancesToHotels();

                // Stop listening for location updates
                locationManager.removeUpdates(this);
            }
        };

        // Check for permission and request location updates
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            // Request permission if not granted
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        // Load the hotels From the local Data
        //Todo: {execute the fetching data in a separate thread}

        /*hotels = (ArrayList<Hotel>) new HotelUtil(getContext()).getAllHotels();*/
        return  rootView;
    }

    private boolean isConnectedToInternet() {
        // Check The connectivity of The user
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    // Todo: Replace the asynckTask with executor

    @SuppressLint("StaticFieldLeak")
    class FetchWeatherDataTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result;
        }


        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonResult = new JSONObject(result);
                JSONArray weatherArray = jsonResult.getJSONArray("weather");
                if (weatherArray.length() > 0) {
                    JSONObject weatherObject = weatherArray.getJSONObject(0);

                    // Extract weather information
                    String weatherDescription = weatherObject.getString("description");
                    String weatherIcon = weatherObject.getString("icon");
                    double temperature = jsonResult.getJSONObject("main").getDouble("temp");

                    // Update the views with the retrieved data
                    weatherDescriptionTextView.setText(weatherDescription);
                    weatherTemperatureTextView.setText(convertCelsius(temperature) + "°C");

                    // Set the weather icon based on the weather condition
                    int weatherIconResourceId = getWeatherIconResource(weatherIcon);
                    weatherIconImageView.setImageResource(weatherIconResourceId);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    private int convertCelsius(double temperature) {
        return (int) Math.ceil(temperature - 274.15);
    }


    // get The icon corresponding to the Whether Status
    private int getWeatherIconResource(String weatherIcon) {
        switch (weatherIcon) {
            case "01d":
                return R.drawable.icon_sunny;
            case "01n":
                return R.drawable.icon_clear_night;
            case "02d":
            case "02n":
                return R.drawable.icon_partly_cloudy;
            case "03d":
            case "03n":
            case "04d":
            case "04n":
                return R.drawable.icon_cloudy;
            case "09d":
            case "09n":
            case "10d":
            case "10n":
                return R.drawable.icon_rainy;
            case "11d":
            case "11n":
                return R.drawable.icon_thunderstorm;
            case "13d":
            case "13n":
                return R.drawable.icon_snowy;
            case "50d":
            case "50n":
                return R.drawable.icon_mist;
            default:
                return R.drawable.icon_default;
        }
    }

}