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
        String resId = String.valueOf(intent.getIntExtra("R_number", 0)).toString();
        MapData task = new MapData();
        task.execute(resId);
        //지연

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

    class MapData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String temp;
            String str_lat;
            String str_lon;

            try {
                JSONObject json = new JSONObject(result);
                temp = json.getString("response");
                JSONArray ary = new JSONArray(temp);

                JSONObject js = ary.getJSONObject(0);
                r_name = js.getString("name");
                str_lat = js.getString("latitude");
                str_lon = js.getString("longitude");

                lat = Float.parseFloat(str_lat);
                lon = Float.parseFloat(str_lon);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            String R_Number = (String)params[0];

            String serverURL = "http://jaejindb.cafe24.com/RestaurantMap.php"; // 주소
            String postParameters = "R_Number=" + R_Number;

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(1000);
                httpURLConnection.setConnectTimeout(1000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString();

            } catch (Exception e) {

                return new String("Error: " + e.getMessage());
            }
        }
    }
}
