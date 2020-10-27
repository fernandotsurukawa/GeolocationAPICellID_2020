package fernando.com.geolocationapicellid;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        Bundle extras = getIntent().getExtras();

        double latitude = extras.getDouble("latitude");
        double longitude = extras.getDouble("longitude");
        double accuracy = extras.getDouble("accuracy");

        LatLng latLng = new LatLng(latitude, longitude);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                latLng, 17
        ));

        MarkerOptions mOpt = new MarkerOptions().title("Você está aqui").position(latLng);
        mMap.addMarker(mOpt);

        double latitudeCasa = -22.952722911612984;
        double longitudeCasa =  -43.17694158745668;
        LatLng latLngCasa = new LatLng(latitudeCasa, longitudeCasa);

        MarkerOptions mOptCasa = new MarkerOptions().title("Casa").position(latLngCasa).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(mOptCasa);

        double latitudeIME = -22.95589039123042;
        double longitudeIME =  -43.166305197623586;
        LatLng latLngIME = new LatLng(latitudeIME, longitudeIME);

        MarkerOptions mOptIME = new MarkerOptions().title("IME").position(latLngIME).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(mOptIME);

        // Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions()
                .center(latLng)
                .radius(accuracy)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#2271cce7"))
                .fillColor(Color.parseColor("#2271cce7"));

        // Get back the mutable Circle
        //Circle circle = mMap.addCircle(circleOptions);
        mMap.addCircle(circleOptions);



        double latitude1 = -22.9169;
        double longitude1 =  -43.1947;
        LatLng latLng1 = new LatLng(latitude1, longitude1);
        MarkerOptions mOpt1 = new MarkerOptions().title("ERB1").position(latLng1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt1);

        CircleOptions circleOptions1 = new CircleOptions()
                .center(latLng1)
                .radius(1000)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#5571cce7"))
                .fillColor(Color.parseColor("#5571cce7"));

        mMap.addCircle(circleOptions1);

        double latitude2 = -22.9109;
        double longitude2 =  -43.2019;
        LatLng latLng2 = new LatLng(latitude2, longitude2);
        MarkerOptions mOpt2 = new MarkerOptions().title("ERB2").position(latLng2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt2);

        CircleOptions circleOptions2 = new CircleOptions()
                .center(latLng2)
                .radius(1000)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#7771cce7"))
                .fillColor(Color.parseColor("#7771cce7"));
        mMap.addCircle(circleOptions2);

        double latitude3 = -22.9037;
        double longitude3 =  -43.1951;
        LatLng latLng3 = new LatLng(latitude3, longitude3);
        MarkerOptions mOpt3 = new MarkerOptions().title("ERB3").position(latLng3).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt3);

        CircleOptions circleOptions3 = new CircleOptions()
                .center(latLng3)
                .radius(1000)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#7771cce7"))
                .fillColor(Color.parseColor("#7771cce7"));
        mMap.addCircle(circleOptions3);

        double latitude4 = -22.9227;
        double longitude4 =  -43.2145;
        LatLng latLng4 = new LatLng(latitude4, longitude4);
        MarkerOptions mOpt4 = new MarkerOptions().title("ERB4").position(latLng4).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt4);

        CircleOptions circleOptions4 = new CircleOptions()
                .center(latLng4)
                .radius(1000)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#5571cce7"))
                .fillColor(Color.parseColor("#5571cce7"));
        mMap.addCircle(circleOptions4);

        double latitude5 = -22.8954;
        double longitude5 =  -43.2012;
        LatLng latLng5 = new LatLng(latitude5, longitude5);
        MarkerOptions mOpt5 = new MarkerOptions().title("ERB5").position(latLng5).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt5);

        CircleOptions circleOptions5 = new CircleOptions()
                .center(latLng5)
                .radius(1000)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#2271cce7"))
                .fillColor(Color.parseColor("#2271cce7"));
        mMap.addCircle(circleOptions5);

        double latitude6 = -22.9043;
        double longitude6 =  -43.181;
        LatLng latLng6 = new LatLng(latitude6, longitude6);
        MarkerOptions mOpt6 = new MarkerOptions().title("ERB6").position(latLng6).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt6);

        CircleOptions circleOptions6 = new CircleOptions()
                .center(latLng6)
                .radius(1000)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#7771cce7"))
                .fillColor(Color.parseColor("#7771cce7"));
        mMap.addCircle(circleOptions6);

        double latitude7 = -22.9053;
        double longitude7 =  -43.1758;
        LatLng latLng7 = new LatLng(latitude7, longitude7);
        MarkerOptions mOpt7 = new MarkerOptions().title("ERB7").position(latLng7).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt7);

        CircleOptions circleOptions7 = new CircleOptions()
                .center(latLng7)
                .radius(1000)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#7771cce7"))
                .fillColor(Color.parseColor("#7771cce7"));
        mMap.addCircle(circleOptions7);

        double latitude8 = -22.9136;
        double longitude8 =  -43.1782;
        LatLng latLng8 = new LatLng(latitude8, longitude8);
        MarkerOptions mOpt8= new MarkerOptions().title("ERB8").position(latLng8).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt8);

        CircleOptions circleOptions8 = new CircleOptions()
                .center(latLng8)
                .radius(1000)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#7771cce7"))
                .fillColor(Color.parseColor("#7771cce7"));
        mMap.addCircle(circleOptions8);

        double latitude9 = -22.9019;
        double longitude9 =  -43.2121;
        LatLng latLng9 = new LatLng(latitude9, longitude9);
        MarkerOptions mOpt9 = new MarkerOptions().title("ERB9").position(latLng9).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt9);

        CircleOptions circleOptions9 = new CircleOptions()
                .center(latLng9)
                .radius(1000)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#2271cce7"))
                .fillColor(Color.parseColor("#2271cce7"));
        mMap.addCircle(circleOptions9);

        double latitude10 = -22.9233;
        double longitude10 =  -43.2075;
        LatLng latLng10 = new LatLng(latitude10, longitude10);
        MarkerOptions mOpt10 = new MarkerOptions().title("ERB10").position(latLng10).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        //mMap.addMarker(mOpt10);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        }
    }
}
