package roman.game.teslaeggdetection;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentMap extends Fragment {

    private AppCompatActivity activity;
    private SupportMapFragment supportMapFragment;

    public FragmentMap(){};

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        findViews(view);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(onMapReadyCallback);

        return view;
    }

    private void findViews(View view) {

    }

    public void setLocation(double latitude, double longitude) {
        Log.d("pttt", "lat " + latitude+" long: " + longitude);
        LatLng latLng = new LatLng(latitude,longitude);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("loc"));
                MarkerOptions markerOptions = new MarkerOptions();
                // Set the position of marker
                markerOptions.position(latLng);
                // Set title of the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                // remove all marker
                googleMap.clear();
                // Animating to zoom into the marker position
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));
                // Add marker on the map
                googleMap.addMarker(markerOptions);
            }
        });
    }

    OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            // Set center (0,0) cords as map location
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(0, 0))
                    .title("Center"));

            // on click listener
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    // When clicked on the map
                    // initialize marker options
                    MarkerOptions markerOptions = new MarkerOptions();
                    // Set the position of marker
                    markerOptions.position(latLng);
                    // Set title of the marker
                    markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                    // remove all marker
                    googleMap.clear();
                    // Animating to zoom into the marker position
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,14));
                    // Add marker on the map
                    googleMap.addMarker(markerOptions);
                }
            });
        }
    };
}
