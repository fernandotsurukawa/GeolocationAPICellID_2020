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
