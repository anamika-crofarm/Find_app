package com.example.find;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.find.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class maps extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    Button btn;
    ActivityMapsBinding binding;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    public static final int REQUEST_CODE = 101;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference databaseReference ;
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();
        geocoder = new Geocoder(this);
    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null)
                {
                    currentLocation = location;
                  //  Toast.makeText(getApplicationContext(),currentLocation.getLatitude()+" "+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(maps.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final LatLng[] latLng = {new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude())};
        MarkerOptions markerOptions = new MarkerOptions().position(latLng[0]).title("you are here");
       googleMap.addMarker(markerOptions);


        String location = getIntent().getStringExtra("location");
        String business = getIntent().getStringExtra("business");

        try {
            List<Address> address = geocoder.getFromLocationName(location,1);

            Address address1 = address.get(0);
            MarkerOptions markerOptions1 = new MarkerOptions()
                    .position(new LatLng(address1.getLatitude(),address1.getLongitude()))
                    .title(business.toString()+","+address1.getLocality());
            googleMap.addMarker(markerOptions1);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(address1.getLatitude(),address1.getLongitude()),16));

        } catch (IOException e) {
            e.printStackTrace();
        }












    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    fetchLastLocation();
                }
                break;
        }
    }
}