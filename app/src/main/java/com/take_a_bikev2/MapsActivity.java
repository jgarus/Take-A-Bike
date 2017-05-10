package com.take_a_bikev2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
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
import java.util.Random;

import layout.About;
import layout.Areas;
import layout.Help;
import layout.UserProfile;

/**
 * Created by David on 2/21/2017.
 */


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener, NavigationView.OnNavigationItemSelectedListener {

    private SelectFragment selectFragment;

    private Toolbar toolbar;
    //private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public GoogleMap mMap;
    private LocationSource.OnLocationChangedListener mListener;
    private List<Marker> originMarkers = new ArrayList<>();

    private GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    int min = 1;
    int max = 20;

    Random r = new Random();
    int bikedek = r.nextInt(max - min + 1) + min;
    int bikedek1 = r.nextInt(max - min + 1) + min;
    int bikedek2 = r.nextInt(max - min + 1) + min;
    int bikedek3 = r.nextInt(max - min + 1) + min;
    int bikedek4 = r.nextInt(max - min + 1) + min;
    int bikedek5 = r.nextInt(max - min + 1) + min;
    int bikedek6 = r.nextInt(max - min + 1) + min;
    int bikedek7 = r.nextInt(max - min + 1) + min;
    int bikedek8 = r.nextInt(max - min + 1) + min;
    int bikedek9 = r.nextInt(max - min + 1) + min;
    int bikedek10 = r.nextInt(max - min + 1) + min;
    int bikedek11 = r.nextInt(max - min + 1) + min;
    int bikedek12= r.nextInt(max - min + 1) + min;
    int bikedek13 = r.nextInt(max - min + 1) + min;
    int bikedek14 = r.nextInt(max - min + 1) + min;
    int bikedek15 = r.nextInt(max - min + 1) + min;
    int bikedek16 = r.nextInt(max - min + 1) + min;
    int bikedek17 = r.nextInt(max - min + 1) + min;
    String stations;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Log.e("css", String.valueOf(setContentView(R.layout.activity_maps)));
        //Initialize Fragment
        selectFragment = new SelectFragment();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setTools();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        //Buildings
        LatLng studentunion = new LatLng(35.308607, -80.733736);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Student Union") + ": " + bikedek + " availability").position(studentunion)));

        LatLng woodword = new LatLng(35.307520, -80.735719);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Woodword Hall") + ": " + bikedek + " availability").position(woodword)));

        LatLng StudentActivityCenter = new LatLng(35.3066644, -80.7366597);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Student Activity Center") + ": " + bikedek2 + " availability").position(StudentActivityCenter)));

        LatLng AtkinsLibrary = new LatLng(35.3056584, -80.7344607);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Atkins Library") + ": " + bikedek3 + " availability").position(AtkinsLibrary)));

        LatLng Burson = new LatLng(35.3062627, -80.7330765);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Burson") + ": " + bikedek4 + " availability").position(Burson)));

        LatLng smith = new LatLng(35.3065323, -80.7331744);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Smith") + ": " + bikedek5 + " availability").position(smith)));

        LatLng epic = new LatLng(35.3087212,-80.7405288);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Epic") + ": " + bikedek6 + " availability").position(epic)));

        LatLng CHHS = new LatLng(35.307174,-80.73330820000001);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("CHHS") + ": " + bikedek12 + " availability").position(CHHS)));

        //Parking Decks
        LatLng WestDeck = new LatLng(35.3054649,-80.73651269999999);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("West Deck") + ": " + bikedek7 + " availability").position(WestDeck)));

        LatLng UnionDeck = new LatLng(35.3091261,-80.73547339999999);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Union Deck") + ": " + bikedek8 + " availability").position(UnionDeck)));

        LatLng EastDeck = new LatLng(35.3047717,-80.72750510000003);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("East Deck") + ": " + bikedek9 + " availability").position(EastDeck)));

        LatLng NorthDeck = new LatLng(35.3134891,-80.73140269999999);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("North Deck") + ": " + bikedek10 + " availability").position(NorthDeck)));

        LatLng CRIDeck = new LatLng(35.30913650000001,-80.74334579999999);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("CRI Deck") + ": " + bikedek11 + " availability").position(CRIDeck)));

        //Housing
        LatLng HolshouserHall = new LatLng(35.3021154,-80.73607959999998);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Holshouser Hall") + ": " + bikedek13).position(HolshouserHall)));

        LatLng LynchHall = new LatLng(35.3104716,-80.7341743);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Lynch Hall") + ": " + bikedek14).position(LynchHall)));

        LatLng MartinHall = new LatLng(35.3100312,-80.72745399999997);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Martin Hall") + ": " + bikedek15).position(MartinHall)));

        LatLng Fretwell = new LatLng(35.3060067,-80.7293588);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Fretwell") + ": " + bikedek16).position(Fretwell)));

        LatLng RobinsonHall = new LatLng(35.303809,-80.72993550000001);
        originMarkers.add(mMap.addMarker(new MarkerOptions().title(stations =("Robinson Hall") + ": " + bikedek17).position(RobinsonHall)));

        //Marker info window click event
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(MapsActivity.this, SelectActivity.class);
                Bundle extras = new Bundle();
                extras.putString("Stations", stations); //key
                extras.putInt("Availability", bikedek); //value
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }

    //Initialize Google Api
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    //Checks permission before adding current location
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            //Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                //No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    //Side menu
    private void setTools() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //a place to put the drawer.xml
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Initializing the drawer.xml layout and actionbar toggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //calling the method displayselectedscreen and passing the id of selected menu
        Log.e("inside", String.valueOf(item.getItemId()));
        Log.e("maps", String.valueOf(R.id.map));
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }

    public void displaySelectedScreen(int itemId) {
        //Creating fragment object
        Fragment fragment = null;
        //Initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_userprofile:
                fragment = new UserProfile();
                break;
            case R.id.nav_areas:
                fragment = new Areas();
                break;
            case R.id.nav_help:
                fragment = new Help();
                break;
            case R.id.nav_about:
                fragment = new About();
                break;
            case R.id.map:
               // fragment =
                break;
        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentManager fm = getSupportFragmentManager();
        //Replacing the fragment
        //ft.remove(fragment).commit();

        if ((fragment != null) && (itemId!=R.id.map)) {
            ft.replace(R.id.flContent, fragment);
            ft.commit();
        } else {
            ///ft.remove(fragment).commit();
            //ft.addToBackStack();
            //ft.commit();
            //ft.add(R.id.map, fragment).addToBackStack("fragBack").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //Check is device is connected; WiFi, Cellular, GPS
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        //ADD TRY CATCH
        //Toast.makeText(this, "Connection Suspended", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //ADD TRY CATCH
        //Toast.makeText(this, "Connection Failed", Toast.LENGTH_LONG).show();
    }

    //If location is null add to current location
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //Log.e("location1", Double.toString(location.getLatitude()));
        //Log.e("location2", Double.toString(location.getLongitude()));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 17.0f));

        //Stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }


}
