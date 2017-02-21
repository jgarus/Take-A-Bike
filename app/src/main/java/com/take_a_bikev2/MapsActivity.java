package com.take_a_bikev2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 2/21/2017.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Marker> originMarkers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng studentunion = new LatLng(35.308607, -80.733736);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(studentunion, 300));
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Student Union").position(studentunion)));

        LatLng woodword = new LatLng(35.307520, -80.735719);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Woodword hall").position(woodword)));

        LatLng StudentActivityCenter = new LatLng(35.3066644, -80.7366597);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Student Activity Center").position(StudentActivityCenter)));


        LatLng AtkinsLibrary = new LatLng(35.3056584, -80.7344607);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Atkins Library").position(AtkinsLibrary)));


        LatLng Burson = new LatLng(35.3062627, -80.7330765);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Burson").position(Burson)));

        LatLng smith = new LatLng(35.3065323, -80.7331744);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Smith").position(smith)));


    }
}
