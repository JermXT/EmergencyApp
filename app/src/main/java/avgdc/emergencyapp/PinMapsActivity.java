package avgdc.emergencyapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class PinMapsActivity extends FragmentActivity implements OnMapReadyCallback {


    ArrayList<LatLng> location = new ArrayList<>();
    private GoogleMap mMap;
    double lat;
    double lon;
    String type;
    Circle circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        lat = 37.7656322;
        lon = -121.96330590000002;
        type = "You Are Here";
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(0, 0))
                .radius(0)
                .strokeColor(Color.BLUE)
                );
        // Add a marker in Sydney and move the camera
        LatLng SanRamon = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(SanRamon).title(type));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SanRamon));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                Toast.makeText(getApplicationContext(), point.toString(), Toast.LENGTH_SHORT).show();

                mMap.addMarker(new MarkerOptions().position(point).title(type));
                location.add(point);
                double lg = 0.0;
                double la = 0.0;
                for(LatLng pos : location){
                    lg+=pos.longitude;
                    la += pos.latitude;
                }
                la = la/location.size();
                lg = lg/location.size();
                double maxdist = 0.0;
                for(LatLng pos : location){
                    double dist = Math.sqrt(Math.pow((pos.longitude-lg),2)+Math.pow((pos.latitude-la),2));

                    if(dist > maxdist){
                        maxdist = dist;
                    }
                }
                //maxdist = 20;
                maxdist = maxdist *111000;
                System.out.println(maxdist);

                circle.setCenter(new LatLng(la, lg));
                circle.setRadius(maxdist);

                //System.out.println(location);

            }
        });


    }





}
