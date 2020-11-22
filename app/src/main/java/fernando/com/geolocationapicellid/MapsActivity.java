package fernando.com.geolocationapicellid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
        double latitude2 = extras.getDouble("latitude2");
        double longitude2 = extras.getDouble("longitude2");
        double latitudeGps = extras.getDouble("latitudeGps");
        double longitudeGps = extras.getDouble("longitudeGps");

        int[] numEstacao = {
                403095573,
                413581551,
                413581888,
                413583147,
                441044921,
                441045057,
                682700347,
                682710610,
                682710660,
                683938908,
                683983415,
                684830256,
                686130600,
                686131126,
                687482240,
                687507855,
                687507871,
                689432658,
                689625235,
                689996209,
                690203896,
                690741146,
                690741146,
                696849917,
                697662870,
                697932796,
                699484332,
                699484979,
                1000186994,
                1000187125,
                1000187400,
                1000187613,
                1000188849,
                1000392004,
                1000404584,
                1000405424,
                1001290019,
                1001301487,
                1003917124,
                1003917221,
                1003925089,
                1003925232,
                1003925526,
                1004793836,
                1004912096,
                1005136260,
                1005229659,
                1005229667,
                1006682314,
                1006805629,
                1006954403,
                1008318458,
                1010244326,
                1010244326,
                1006954403,
                1005229659,
                1004793836,
                1005136260
        };

        double[] latitudeERB = {
                -22.9547,
                -22.95118056,
                -22.94564167,
                -22.9545,
                -22.95721944,
                -22.95166667,
                -22.94721944,
                -22.95358056,
                -22.94978056,
                -22.9435,
                -22.9534,
                -22.9578,
                -22.958,
                -22.9536,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.9571,
                -22.9565,
                -22.951,
                -22.94870833,
                -22.95582778,
                -22.95722222,
                -22.9423,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.91173056,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.94196944,
                -22.94903056,
                -22.95449444,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.9545,
                -22.94946944,
                -22.94105833,
                -22.82086111,
                -22.95604444,
                -22.95436944,
                -22.95139444,
                -22.95450278,
                -22.9525,
                -22.95449722,
                -22.94021389,
                -22.94021389,
                -22.9525,
                -22.95604444,
                -22.94946944,
                -22.82086111
        };

        double[] longitudeERB = {
            -43.1944,
            -43.17595,
            -43.18606111,
            -43.18738889,
            -43.17613889,
            -43.18528056,
            -43.18281111,
            -43.17853056,
            -43.18868889,
            -43.18310278,
            -43.182,
            -43.1911,
            -43.1962,
            -43.19010278,
            -43.18738889,
            -43.18738889,
            -43.18738889,
            -43.1948,
            -43.1827,
            -43.1921,
            -43.18048889,
            -43.17665833,
            -43.17613889,
            -43.1816,
            -43.18738889,
            -43.18738889,
            -43.18738889,
            -43.22817222,
            -43.18738889,
            -43.18738889,
            -43.18738889,
            -43.18738889,
            -43.18738889,
            -43.18738889,
            -43.18738889,
            -43.18744444,
            -43.18064444,
            -43.18456944,
            -43.18736944,
            -43.18738889,
            -43.18738889,
            -43.18738889,
            -43.18738889,
            -43.18885,
            -43.17900833,
            -43.3662,
            -43.17883611,
            -43.1862,
            -43.18481111,
            -43.18738889,
            -43.1967,
            -43.18738611,
            -43.18088611,
            -43.18088611,
            -43.1967,
            -43.17883611,
            -43.18885,
            -43.3662
        };

        LatLng latLng = new LatLng(latitude, longitude);
        LatLng latLng2 = new LatLng(latitude2, longitude2);
        LatLng latLngGps = new LatLng(latitudeGps, longitudeGps);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                latLng, 17
        ));

        MarkerOptions mOpt = new MarkerOptions().title("Google Geolocation").position(latLng);
        mMap.addMarker(mOpt);

        MarkerOptions mOpt2 = new MarkerOptions().title("OpenCelliD").position(latLng2);
        mMap.addMarker(mOpt2);

        MarkerOptions mOptGps = new MarkerOptions().title("GPS").position(latLngGps);
        mMap.addMarker(mOptGps);

        for(int i=0; i<numEstacao.length; i++)
            mMap.addMarker(new MarkerOptions().title(String.format("CellID: %d", numEstacao[i])).position(new LatLng(latitudeERB[i], longitudeERB[i])).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        /*double latitudeCasa = -22.952722911612984;
        double longitudeCasa =  -43.17694158745668;
        LatLng latLngCasa = new LatLng(latitudeCasa, longitudeCasa);

        MarkerOptions mOptCasa = new MarkerOptions().title("Casa").position(latLngCasa).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(mOptCasa);

        double latitudeIME = -22.95589039123042;
        double longitudeIME =  -43.166305197623586;
        LatLng latLngIME = new LatLng(latitudeIME, longitudeIME);

        MarkerOptions mOptIME = new MarkerOptions().title("IME").position(latLngIME).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        mMap.addMarker(mOptIME);*/

        /*// Instantiates a new CircleOptions object and defines the center and radius
        CircleOptions circleOptions = new CircleOptions()
                .center(latLng)
                .radius(accuracy)
                .strokeWidth(0)
                .strokeColor(Color.parseColor("#2271cce7"))
                .fillColor(Color.parseColor("#2271cce7"));

        // Get back the mutable Circle
        //Circle circle = mMap.addCircle(circleOptions);
        mMap.addCircle(circleOptions);*/

        /*double latitudeERB1 = -22.9449694444444;
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
        mMap.addMarker(mOptERB20);*/
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
