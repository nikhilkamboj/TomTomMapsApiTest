package androidapp.com.tomtommapsapitest;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.CameraPosition;
import com.tomtom.online.sdk.map.MapFragment;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.SimpleMarkerBalloon;
import com.tomtom.online.sdk.map.TomtomMap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    //MapFragment mapView;
    TomtomMap map;
    EditText searchText;
    Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         searchText = findViewById(R.id.searchEditText);
         searchButton = findViewById(R.id.searchButton);
        //mapView = (MapView) findViewById(R.id.map_view);
        final MapFragment mapFragment = (MapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_view);
        mapFragment.getAsyncMap(this);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchLocation = searchText.getText().toString();
                // call get current longitude and latitude method
                mapFragment.getAsyncMap(MainActivity.this);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull TomtomMap tomtomMap) {
        this.map = tomtomMap;
        tomtomMap.setMyLocationEnabled(true);
        Location userLocation = tomtomMap.getUserLocation();
        LatLng amsterdam = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
        SimpleMarkerBalloon balloon = new SimpleMarkerBalloon("MyLocation");
        tomtomMap.addMarker(new MarkerBuilder(amsterdam).markerBalloon(balloon));
        tomtomMap.centerOn(CameraPosition.builder(amsterdam).zoom(15).build());
        map.getUiSettings().turnOnRasterTrafficIncidents();
        map.getUiSettings().turnOnRasterTrafficFlowTiles();
    }


//    private LatLng getCurrentLatLng(String location) {}
}