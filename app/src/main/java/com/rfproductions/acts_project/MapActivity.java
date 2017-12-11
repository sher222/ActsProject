package com.rfproductions.acts_project;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import android.util.Log;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;
class ActsEvents {
    String locality;
    String title;
    double coordinate_1;
    double coordinate_2;
    boolean connected = false;
    ActsEvents(String t, String eventLocality, double c1, double c2, boolean isConnected) {
        title = t;
        locality = eventLocality;
        coordinate_1=c1;
        coordinate_2=c2;
        connected = isConnected;
    }
}
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    ArrayList<ActsEvents> actsEventses;
    void initEvents() {
        actsEventses = new ArrayList<>();
        actsEventses.add(new ActsEvents("Jesus Ascends to Heaven", "Mount of Olives", 31.7779492, 35.2369221, false));
        actsEventses.add(new ActsEvents("Disciples Celebrate Pentecost", "Jerusalem", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Peter and John Heal Lame Man", "The Beautiful Gate", 31.778286, 35.235393, false));
        actsEventses.add(new ActsEvents("Peter and John Preach to the Jews", "The Beautiful Gate", 31.778286,  35.235393, false));
        actsEventses.add(new ActsEvents("Ananias and Sapphira Die", "Jerusalem", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Apostles Arrested and Released", "Jerusalem", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Stephen Is Martyred", "Jerusalem", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Phillip Converts Simon the Sorcerer", "Samaria", 32.478051,  35.294222, false));
        actsEventses.add(new ActsEvents("Phillip Baptizes Ethiopian Eunuch", "Road Between Gaza and Jerusalem", 31.502806, 34.463494, false));
        actsEventses.add(new ActsEvents("Saul Is Converted", "Road to Damascus", 33.513343, 36.294267, false));
        actsEventses.add(new ActsEvents("Saul Escapes to Jerusalem", "Jerusalem", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Peter Heals Aeneas and Dorcas", "Lydda", 32.034909, 34.889890, false));
        actsEventses.add(new ActsEvents("Peter Converts Cornelius", "Caeserea", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Paul Visits the Church in Antioch", "Antioch", 36.245052, 36.161439, false));
        actsEventses.add(new ActsEvents("Peter Is Arrested and Escapes", "Jerusalem", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Paul and Barnabas Sent on the First Mission Trip", "Jerusalem", 36.245052, 36.161439, true));
        actsEventses.add(new ActsEvents("Paul Converts an Official", "Cyprus", 34.772509, 32.424133, true));
        actsEventses.add(new ActsEvents("Paul in Pisidia", "Pisidian Antioch", 38.348633, 31.176502, true));
        actsEventses.add(new ActsEvents("Paul in Iconium", "Iconium", 37.883530, 32.494262, true));
        actsEventses.add(new ActsEvents("Paul Heals a Lame Man", "Lystra", 37.578134, 32.453182, true));
        actsEventses.add(new ActsEvents("Paul in Derbe", "Derbe", 37.350920, 33.271417, true));
        actsEventses.add(new ActsEvents("Paul Returns to Lystra", "Lystra", 37.578134, 32.453182, true));
        actsEventses.add(new ActsEvents("Paul Returns to Iconium", "Iconium", 37.883530, 32.494262, true));
        actsEventses.add(new ActsEvents("Paul Returns to Antioch", "Antioch", 36.245052, 36.161439, true));
        actsEventses.add(new ActsEvents("Paul Returns to Pisidia", "Pisidian Antioch", 38.348633, 31.176502, true));
        actsEventses.add(new ActsEvents("Paul in Perga", "Perga", 37.005208, 30.904945, true));
        actsEventses.add(new ActsEvents("Paul in Attalia", "Attalia", 36.880825, 30.694565, true));
        actsEventses.add(new ActsEvents("Paul Returns to Antioch", "Antioch", 36.245052, 36.161439, true));
        actsEventses.add(new ActsEvents("Peter and Paul Debate Circumcision", "Jerusalem", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Paul in Syria", "Syria", 33.519299, 36.313449, true));
        actsEventses.add(new ActsEvents("Paul in Derbe", "Derbe", 37.350920, 33.271417, true));
        actsEventses.add(new ActsEvents("Paul Appoints Timothy", "Lystra", 37.578134, 32.453182, true));
        actsEventses.add(new ActsEvents("Paul in Phrygia", "Phrygia", 37.769867, 29.064501, true));
        actsEventses.add(new ActsEvents("Paul in Galatia", "Galatia", 37.578134, 32.453182, true));
        actsEventses.add(new ActsEvents("Paul in Troas", "Troas", 39.506643, 26.080592, true));
        actsEventses.add(new ActsEvents("Paul and Silas Are Arrested", "Phillipi", 41.011959, 24.286190, true));
        actsEventses.add(new ActsEvents("Paul in Thessalonica", "Thessalonica", 40.632155, 22.932086, true));
        actsEventses.add(new ActsEvents("Paul in Berea", "Berea", 40.516700, 22.200000, true));
        actsEventses.add(new ActsEvents("Paul in Athens", "Athens", 37.983333, 23.733333, true));
        actsEventses.add(new ActsEvents("Paul in Corinth", "Corinth", 37.905957, 22.877881, true));
        actsEventses.add(new ActsEvents("Paul in Ephesus", "Ephesus", 37.953314, 27.367825, true));
        actsEventses.add(new ActsEvents("Paul Returns to Jerusalem", "Jerusalem", 31.732204, 35.207756, true));
        actsEventses.add(new ActsEvents("Paul in Antioch", "Antioch", 36.245052, 36.161439, true));
        actsEventses.add(new ActsEvents("Paul in Galatia", "Galatia", 37.578134, 32.453182, true));
        actsEventses.add(new ActsEvents("Paul in Phrygia", "Phrygia", 37.769867, 29.064501, true));
        actsEventses.add(new ActsEvents("Paul Introduces the Holy Spirit", "Ephesus", 37.953314, 27.367825, true));
        actsEventses.add(new ActsEvents("Paul in Macedonia", "Macedonia", 40.632155, 22.932086, true));
        actsEventses.add(new ActsEvents("Paul in Greece", "Greece", 37.983333, 23.733333, true));
        actsEventses.add(new ActsEvents("|Travel|", "Phillipi", 41.011959, 24.286190, true));
        actsEventses.add(new ActsEvents("Paul in Troas", "Troas", 39.506643, 26.080592, true));
        actsEventses.add(new ActsEvents("|Travel|", "Assos", 39.489550, 26.335867, true));
        actsEventses.add(new ActsEvents("|Travel|", "Mitylene", 26.551804,39.100335, true));
        actsEventses.add(new ActsEvents("|Travel|", "Chios", 38.444840, 26.063302, true));
        actsEventses.add(new ActsEvents("|Travel|", "Samos", 37.715172, 26.934167, true));
        actsEventses.add(new ActsEvents("|Travel|", "Miletus", 37.5, 27.3, true));
        actsEventses.add(new ActsEvents("Paul Bids the Ephesians Farewell", "Ephesus", 37.953314, 27.367825, true));
        actsEventses.add(new ActsEvents("|Travel|", "Kos", 36.804670, 27.089940, true));
        actsEventses.add(new ActsEvents("|Travel|", "Rhodes", 36.441926, 28.226721, true));
        actsEventses.add(new ActsEvents("|Travel|", "Patara", 36.274717, 29.318637, true));
        actsEventses.add(new ActsEvents("|Travel|", "Tyre", 33.275827, 35.192574, true));
        actsEventses.add(new ActsEvents("Paul Causes A Riot and Is Arrested", "Jerusalem", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Paul Testifies Before Festus and Agrippa", "Ceasarea", 31.732204, 35.207756, false));
        actsEventses.add(new ActsEvents("Paul Is Shipwrecked on Malta", "Malta", 14.532436,35.852826, true));
        actsEventses.add(new ActsEvents("Paul Continues His Ministry", "Rome", 41.900000, 12.483333, true));


    }

    GoogleMap mGoogleMap;
    private static final String TAG = MapActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        final Button nextButton = (Button)findViewById(R.id.button9);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SeekBar bar = (SeekBar) findViewById(R.id.seek_bar);
                bar.incrementProgressBy(1);
            }
        });

        final Button prevButton = (Button) findViewById(R.id.button7);
        prevButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SeekBar bar = (SeekBar) findViewById(R.id.seek_bar);
                bar.incrementProgressBy(-1);
            }
        });

        SeekBar bar = (SeekBar) findViewById(R.id.seek_bar);
        bar.incrementProgressBy(1);
        bar.setProgress(0);
        bar.setMax(60);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Marker abc;
                ActsEvents PrevEvent = actsEventses.get(0);
                boolean isFirst = true;
                ArrayList<Marker> myMarkers = new ArrayList<Marker>();
                mGoogleMap.clear();
                for (int c = 0; c < i; c++) {
                    boolean found = false;
                    //go through each marker to see whether the there are matching coordinates of this event
                    for (int j = 0; j < myMarkers.size(); j++) {
                        if(myMarkers.get(j).getPosition().latitude == actsEventses.get(c).coordinate_1 &&
                                myMarkers.get(j).getPosition().longitude == actsEventses.get(c).coordinate_2) {
                            found = true;
                            myMarkers.get(j).setTitle(myMarkers.get(j).getTitle() + "; " + actsEventses.get(c).title);
                            break;
                        }

                    }
                    //only if there are no existing events with matching coordinates do we add a new marker
                    if (found == false) {
                        MarkerOptions options = new MarkerOptions()
                                .title(actsEventses.get(c).title)
                                .position(new LatLng(actsEventses.get(c).coordinate_1, actsEventses.get(c).coordinate_2));
                        abc = mGoogleMap.addMarker(options);
                        myMarkers.add(abc);
                    }
                //now we need to add a line if needed
                    if (isFirst) {
                        PrevEvent = actsEventses.get(c);
                        isFirst = false;
                        continue;
                    } else {
                        if (PrevEvent.connected == true) {
                            Polyline myLine = mGoogleMap.addPolyline(new PolylineOptions()
                            .clickable(true)
                            .add(
                                    new LatLng(PrevEvent.coordinate_1, PrevEvent.coordinate_2),
                                    new LatLng(actsEventses.get(c).coordinate_1, actsEventses.get(c).coordinate_2)
                            ));
                        }
                        PrevEvent = actsEventses.get(c);
                    }
                }
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        if (GoogleServicesAvailable()) {
            Toast.makeText(this, "Google Play Services was successfully reached", Toast.LENGTH_LONG).show();
            initMap();
        }
        initEvents();


    }

    private void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    public boolean GoogleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Google Play Services could not be reached", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        goToLocation(38.120950, 24.046957, 4);
        mGoogleMap.getUiSettings().setScrollGesturesEnabled(false);
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

    }

    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    }

    private void goToLocation(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
    }
}
