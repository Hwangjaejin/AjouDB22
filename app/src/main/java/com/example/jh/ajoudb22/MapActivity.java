package com.example.jh.ajoudb22;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static float lat = 0;
    private static float lon = 0;
    private static String r_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        r_name = intent.getStringExtra("Name");
        lat = Float.parseFloat(intent.getStringExtra("Latitude"));
        lon = Float.parseFloat(intent.getStringExtra("Longitude"));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lon))
                .title(r_name));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lat, lon)));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);
        googleMap.animateCamera(zoom);
    }
}
