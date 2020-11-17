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

        double latitudeERB1 = -22.9449694444444;
        double longitudeERB1 =  -44.5680555555556;
        LatLng latLngERB1 = new LatLng(latitudeERB1, longitudeERB1);
        MarkerOptions mOptERB1 = new MarkerOptions().title("CLARO1: 442502460").position(latLngERB1).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB1);

        double latitudeERB2 = -22.9528694444444;
        double longitudeERB2 =  -43.1689083333333;
        LatLng latLngERB2 = new LatLng(latitudeERB2, longitudeERB2);
        MarkerOptions mOptERB2 = new MarkerOptions().title("CLARO2: 684127369").position(latLngERB2).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB2);

        double latitudeERB3 = -22.9429305555556;
        double longitudeERB3 =  -44.5455777777778;
        LatLng latLngERB3 = new LatLng(latitudeERB3, longitudeERB3);
        MarkerOptions mOptERB3 = new MarkerOptions().title("CLARO3: 1000712084").position(latLngERB3).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB3);

        double latitudeERB4 = -22.9546861111111;
        double longitudeERB4 =  -44.5950222222222;
        LatLng latLngERB4 = new LatLng(latitudeERB4, longitudeERB4);
        MarkerOptions mOptERB4 = new MarkerOptions().title("CLARO4: 1002343167").position(latLngERB4).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB4);

        double latitudeERB5 = -22.9528694444444;
        double longitudeERB5 =  -43.1689083333333;
        LatLng latLngERB5 = new LatLng(latitudeERB5, longitudeERB5);
        MarkerOptions mOptERB5 = new MarkerOptions().title("CLARO5: 97692014").position(latLngERB5).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB5);

        double latitudeERB6 = -22.9547;
        double longitudeERB6 =  -43.1944;
        LatLng latLngERB6 = new LatLng(latitudeERB6, longitudeERB6);
        MarkerOptions mOptERB6 = new MarkerOptions().title("CLARO6: 403095573").position(latLngERB6).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB6);

        double latitudeERB7 = -22.9511805555556;
        double longitudeERB7 =  -43.17595;
        LatLng latLngERB7 = new LatLng(latitudeERB7, longitudeERB7);
        MarkerOptions mOptERB7 = new MarkerOptions().title("CLARO7: 413581551").position(latLngERB7).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB7);

        double latitudeERB8 = -22.9456416666667;
        double longitudeERB8 =  -43.1860611111111;
        LatLng latLngERB8 = new LatLng(latitudeERB8, longitudeERB8);
        MarkerOptions mOptERB8 = new MarkerOptions().title("CLARO8: 413581888").position(latLngERB8).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB8);

        double latitudeERB9 = -22.9545;
        double longitudeERB9 =  -43.1873888888889;
        LatLng latLngERB9 = new LatLng(latitudeERB9, longitudeERB9);
        MarkerOptions mOptERB9 = new MarkerOptions().title("CLARO9: 413583147").position(latLngERB9).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB9);

        double latitudeERB10 = -22.9572194444444;
        double longitudeERB10 =  -43.1761388888889;
        LatLng latLngERB10 = new LatLng(latitudeERB10, longitudeERB10);
        MarkerOptions mOptERB10 = new MarkerOptions().title("CLARO10: 441044921").position(latLngERB10).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB10);

        double latitudeERB11 = -22.9516666666667;
        double longitudeERB11 =  -43.1852805555556;
        LatLng latLngERB11 = new LatLng(latitudeERB11, longitudeERB11);
        MarkerOptions mOptERB11 = new MarkerOptions().title("CLARO11: 441045057").position(latLngERB11).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB11);

        double latitudeERB12 = -22.9472194444444;
        double longitudeERB12 =  -43.1828111111111;
        LatLng latLngERB12 = new LatLng(latitudeERB12, longitudeERB12);
        MarkerOptions mOptERB12 = new MarkerOptions().title("CLARO12: 682700347").position(latLngERB12).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB12);

        double latitudeERB13 = -22.9535805555556;
        double longitudeERB13 =  -43.1785305555555;
        LatLng latLngERB13 = new LatLng(latitudeERB13, longitudeERB13);
        MarkerOptions mOptERB13 = new MarkerOptions().title("CLARO13: 682710610").position(latLngERB13).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB13);

        double latitudeERB14 = -22.9497805555556;
        double longitudeERB14 =  -43.1886888888889;
        LatLng latLngERB14 = new LatLng(latitudeERB14, longitudeERB14);
        MarkerOptions mOptERB14 = new MarkerOptions().title("CLARO14: 682710660").position(latLngERB14).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB14);

        double latitudeERB15 = -22.9435;
        double longitudeERB15 =  -43.1831027777778;
        LatLng latLngERB15 = new LatLng(latitudeERB15, longitudeERB15);
        MarkerOptions mOptERB15 = new MarkerOptions().title("CLARO15: 683938908").position(latLngERB15).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB15);

        double latitudeERB16 = -22.9534;
        double longitudeERB16 =  -43.182;
        LatLng latLngERB16 = new LatLng(latitudeERB16, longitudeERB16);
        MarkerOptions mOptERB16 = new MarkerOptions().title("CLARO16: 683983415").position(latLngERB16).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB16);

        double latitudeERB17 = -22.9578;
        double longitudeERB17 =  -43.1911;
        LatLng latLngERB17 = new LatLng(latitudeERB17, longitudeERB17);
        MarkerOptions mOptERB17 = new MarkerOptions().title("CLARO17: 684830256").position(latLngERB17).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB17);

        double latitudeERB18 = -22.958;
        double longitudeERB18 =  -43.1962;
        LatLng latLngERB18 = new LatLng(latitudeERB18, longitudeERB18);
        MarkerOptions mOptERB18 = new MarkerOptions().title("CLARO18: 686130600").position(latLngERB18).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB18);

        double latitudeERB19 = -22.9536;
        double longitudeERB19 =  -43.1901027777778;
        LatLng latLngERB19 = new LatLng(latitudeERB19, longitudeERB19);
        MarkerOptions mOptERB19 = new MarkerOptions().title("CLARO19: 686131126").position(latLngERB19).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB19);

        double latitudeERB20 = -22.9545;
        double longitudeERB20 =  -43.1873888888889;
        LatLng latLngERB20 = new LatLng(latitudeERB20, longitudeERB20);
        MarkerOptions mOptERB20 = new MarkerOptions().title("CLARO20: 687482240").position(latLngERB20).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mMap.addMarker(mOptERB20);

        /*double latitude1 = -22.9169;
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
        //mMap.addMarker(mOpt10);*/
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
