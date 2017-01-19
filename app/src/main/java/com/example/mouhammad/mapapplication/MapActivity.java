package com.example.mouhammad.mapapplication;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    DBHelper db;
    ArrayList<LatLng> asd = new ArrayList<>();
    ListView lv;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        db = new DBHelper(this);
        lv = (ListView) findViewById(R.id.lv);
        Button bt1 = (Button) findViewById(R.id.button2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asd = db.getAllLoc();
                PopulateDat();
                lv.setVisibility(View.VISIBLE);

            }
        });
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Marker m = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(Location.latitude, Location.longitude)));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(m.getPosition()));
        db.addLoc(m.getPosition());
        Toast.makeText(MapActivity.this, "Saved", Toast.LENGTH_LONG).show();

    }
    private void PopulateDat(){
        ArrayAdapter<LatLng> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                asd);
        lv.setAdapter(arrayAdapter);
    }
}
