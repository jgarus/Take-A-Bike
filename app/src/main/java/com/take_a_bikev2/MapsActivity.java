package com.take_a_bikev2;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
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
    private LocationSource.OnLocationChangedListener mListener;
    private List<Marker> originMarkers = new ArrayList<>();

    public static Integer numberSU = 3;
    public static Integer numberWW = 2;
    public static Integer numberSAC = 6;
    public static Integer numberAT = 4;
    public static Integer numberBR = 9;
    public static Integer numberSM = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    public void onLocationChanged(Location location)
    {
        if( mListener != null )
        {
            mListener.onLocationChanged( location );

            //Move the camera to the user's location and zoom in!
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng studentunion = new LatLng(35.308607, -80.733736);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(studentunion, 17.0f));
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12.0f));
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Student Union: " + numberSU).position(studentunion)));

        LatLng woodword = new LatLng(35.307520, -80.735719);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Woodword hall: " + numberWW).position(woodword)));

        LatLng StudentActivityCenter = new LatLng(35.3066644, -80.7366597);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Student Activity Center: " + numberSAC).position(StudentActivityCenter)));


        LatLng AtkinsLibrary = new LatLng(35.3056584, -80.7344607);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Atkins Library: " + numberAT).position(AtkinsLibrary)));


        LatLng Burson = new LatLng(35.3062627, -80.7330765);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Burson: " + numberBR).position(Burson)));

        LatLng smith = new LatLng(35.3065323, -80.7331744);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title("Smith: " + numberSM).position(smith)));


    }
}
