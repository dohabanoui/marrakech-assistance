package com.lst.marrakechassistance.Fragment;

import static android.app.Activity.RESULT_OK;
import static com.mapbox.mapboxsdk.style.expressions.Expression.exponential;
import static com.mapbox.mapboxsdk.style.expressions.Expression.interpolate;
import static com.mapbox.mapboxsdk.style.expressions.Expression.stop;
import static com.mapbox.mapboxsdk.style.expressions.Expression.zoom;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textField;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.textSize;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lst.marrakechassistance.R;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback, PermissionsListener {

    private MapView mapView;
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;
    private double destinationLatitude;
    private double destinationLongitude;

    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        androidx.appcompat.widget.Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        mapView = rootView.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        FloatingActionButton searchFab = rootView.findViewById(R.id.fab_location_search);

        searchFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert Mapbox.getAccessToken() != null;
                Intent intent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken(Mapbox.getAccessToken())
                        .placeOptions(PlaceOptions.builder()
                                .backgroundColor(Color.parseColor("#EEEEEE"))
                                .limit(10)
                                .build(PlaceOptions.MODE_CARDS))
                        .build(requireActivity());
                startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_style_normal) {
            setMapStyle(Style.MAPBOX_STREETS);
            return true;
        } else if (itemId == R.id.menu_style_satellite) {
            setMapStyle(Style.SATELLITE_STREETS);
            return true;
        } else if (itemId == R.id.menu_style_dark) {
            setMapStyle(Style.DARK);
            return true;
        } else if (itemId == R.id.menu_style_light) {
            setMapStyle(Style.LIGHT);
            return true;
        } else if (itemId == R.id.menu_style_outdoors) {
            setMapStyle(Style.OUTDOORS);
            return true;
        } else if (itemId == R.id.menu_style_traffic_day) {
            setMapStyle(Style.TRAFFIC_DAY);
            return true;
        } else if (itemId == R.id.menu_style_traffic_night) {
            setMapStyle(Style.TRAFFIC_NIGHT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setMapStyle(String styleUrl) {
        mapboxMap.setStyle(styleUrl, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                // Mettre à jour le style de carte chargé
            }
        });
    }

   @Override
   public void onMapReady(MapboxMap mapboxMap) {
       this.mapboxMap = mapboxMap;

       mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
           @Override
           public void onStyleLoaded(@NonNull Style style) {
               // Move the camera to the desired starting point
               LatLng startingLatLng = new LatLng(31.6295, -7.9811);
               CameraPosition cameraPosition = new CameraPosition.Builder()
                       .target(startingLatLng)
                       .zoom(12)
                       .build();
               mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 4000, null);

               // Enable location component
               enableLocationComponent(style);

               // Create and configure a SymbolLayer for position names
               SymbolLayer symbolLayer = new SymbolLayer("symbol-layer-id", "source-id");
               symbolLayer.setProperties(
                       textField("{name}"), // Field used to display the position name
                       textSize(
                               interpolate(
                                       exponential(1.5f),
                                       zoom(),
                                       stop(150, 200f), // Text size when zoom is 10 or less
                                       stop(15, 200f)   // Text size when zoom is 15 or more
                               )
                       )
               );

               // Add the SymbolLayer to the map's style
               style.addLayer(symbolLayer);
           }
       });
   }


    private void updateDestination(double latitude, double longitude) {
        destinationLatitude = latitude;
        destinationLongitude = longitude;

        // Mettez à jour le marqueur sur la carte pour indiquer la destination
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title("Destination");
        mapboxMap.clear(); // Efface tous les marqueurs existants
        mapboxMap.addMarker(markerOptions);
    }


    private void enableLocationComponent(Style style) {
        // Vérifier les autorisations de localisation
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {
            // Activer le composant de localisation
            LocationComponent locationComponent = mapboxMap.getLocationComponent();
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(requireContext(), style).build());
            if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling ActivityCompat#requestPermissions here to request the missing permissions
                return;
            }
            locationComponent.setLocationComponentEnabled(true);
            locationComponent.setCameraMode(CameraMode.TRACKING);
            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            // Demander les autorisations de localisation
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(requireActivity());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsManager != null) {
            permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        // Gérer l'explication des autorisations de localisation si nécessaire
        // Vous pouvez implémenter ici la logique pour afficher une explication à l'utilisateur sur la nécessité des autorisations de localisation.
        // Par exemple, vous pouvez afficher une boîte de dialogue ou un message expliquant pourquoi les autorisations sont requises.
        // Assurez-vous d'avoir importé correctement la classe List.
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE && resultCode == RESULT_OK) {
            CarmenFeature selectedCarmenFeature = PlaceAutocomplete.getPlace(data);
            if (selectedCarmenFeature != null) {
                LatLng destinationLatLng = new LatLng(selectedCarmenFeature.center().latitude(),
                        selectedCarmenFeature.center().longitude());

                // Déplace la caméra vers la destination sélectionnée
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(destinationLatLng)
                        .zoom(16)
                        .build();
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 4000, null);

                // Ajoute un marqueur à la position sélectionnée
                mapboxMap.addMarker(new MarkerOptions()
                        .position(destinationLatLng)
                        .title(selectedCarmenFeature.placeName())
                        .snippet(selectedCarmenFeature.text()));
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
