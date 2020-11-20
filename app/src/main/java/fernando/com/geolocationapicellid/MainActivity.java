package fernando.com.geolocationapicellid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class MainActivity extends AppCompatActivity {
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60; // 1 minute
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private TextView txtLatLng;
    private TextView txtAcc;
    private TextView txtIpFlag;
    private TextView txtLatLng2;
    private TextView txtAcc2;
    private TextView txtStatus2;
    private TextView txtBalance2;
    private TextView txtAddress2;
    private TextView txtMessage2;
    private int lac;
    private int cid;
    private int mcc;
    private int mnc;
    private int dbm;
    private int pci;
    private double latitude;
    private double longitude;
    private double accuracy;
    private boolean ipFlag;

    private String status2;
    private int balance2;
    private double latitude2;
    private double longitude2;
    private int accuracy2;
    private String address2;
    private String message2;

    private Location locationGps;
    private double latitudeGps;
    private double longitudeGps;

    private List<Integer> cidList = new ArrayList<>();
    private List<Integer> lacList = new ArrayList<>();
    private List<Integer> mccList = new ArrayList<>();
    private List<Integer> mncList = new ArrayList<>();
    private List<Integer> dbmList = new ArrayList<>();
    private List<Integer> pciList = new ArrayList<>();

    private static final String TAG = "MainActivity";

    private static final int UNAVAILABLE = 2147483647;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtOperator = (TextView) findViewById(R.id.txtOperator);
        TextView txtLac = (TextView) findViewById(R.id.txtLac);
        TextView txtMnc = (TextView) findViewById(R.id.txtMnc);
        TextView txtMcc = (TextView) findViewById(R.id.txtMcc);
        TextView txtCid = (TextView) findViewById(R.id.txtCid);
        TextView txtDbm = (TextView) findViewById(R.id.txtDbm);
        TextView txtPci = (TextView) findViewById(R.id.txtPci);
        txtLatLng = (TextView) findViewById(R.id.txtLatLng);
        txtAcc = (TextView) findViewById(R.id.txtAcc);
        txtIpFlag = (TextView) findViewById(R.id.txtIpFlag);
        txtLatLng2 = (TextView) findViewById(R.id.txtLatLng2);
        txtAcc2 = (TextView) findViewById(R.id.txtAcc2);
        txtAddress2 = (TextView) findViewById(R.id.txtAddress2);
        txtMessage2 = (TextView) findViewById(R.id.txtMessage2);
        final TelephonyManager t = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        assert t != null;
        if (t.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
            final List<CellInfo> cellInfoList = t.getAllCellInfo();
            if (cellInfoList != null) {
                Log.e("CELL INFO DEBUGGING", String.valueOf(cellInfoList));

                String networkOperator = t.getSimOperatorName();

                List<CellInfoLte> cellInfoLteList = (List<CellInfoLte>) (Object) cellInfoList;

                for (CellInfoLte cellInfoLte : cellInfoLteList) {
                    CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();

                    if (cellIdentityLte.getCi() != UNAVAILABLE) {
                        cidList.add(cellIdentityLte.getCi());
                        lacList.add(cellIdentityLte.getTac());
                        mccList.add(cellIdentityLte.getMcc());
                        mncList.add(cellIdentityLte.getMnc());
                        dbmList.add(cellInfoLte.getCellSignalStrength().getDbm());
                        pciList.add(cellIdentityLte.getPci());
                    }
                }

                txtOperator.setText(new StringBuilder().append("Nome da operadora: ").append(networkOperator).toString());

                cid = cidList.get(0);
                lac = lacList.get(0);
                mcc = mccList.get(0);
                mnc = mncList.get(0);
                dbm = dbmList.get(0);
                pci = pciList.get(0);

                txtCid.setText(new StringBuilder().append("Cell ID (CID): ").append(cid).toString());
                txtLac.setText(new StringBuilder().append("Location Area Code (LAC): ").append(lac).toString());
                txtMcc.setText(new StringBuilder().append("Mobile Country Code (MCC): ").append(mcc).toString());
                txtMnc.setText(new StringBuilder().append("Mobile Network Code (MNC): ").append(mnc).toString());
                txtDbm.setText(new StringBuilder().append("Potencia do Sinal (em dBm): ").append(dbm).toString());
                txtPci.setText(new StringBuilder().append("PCI (PCI): ").append(pci).toString());
            }
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //locationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        LocationListener listener = new LocationListener() {

            @Override
            public void onLocationChanged(Location locationGps) {
                // A new location update is received.  Do something useful with it.  In this case,
                // we're sending the update to a handler which then updates the UI with the new
                // location.
                latitudeGps = locationGps.getLatitude();
                longitudeGps = locationGps.getLatitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, listener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, listener);
        locationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    public void getPositionByCellId(View view){

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://www.googleapis.com")
                .build();

        final CellIdService service = adapter.create(CellIdService.class);
        final CellTowers[] cellTowers = new CellTowers[]{new CellTowers(cid, lac, mcc, mnc, dbm)};
        final CellIdRequestParam cellIdRequestParam_considerIpFalse = new CellIdRequestParam("lte", false, cellTowers);
        final CellIdRequestParam cellIdRequestParam_considerIpTrue = new CellIdRequestParam("lte", true, cellTowers);
        service.geolocate( cellIdRequestParam_considerIpFalse , "AIzaSyChKotrFZAIXnWtyzg6NOmuYONb3ASom7A", new Callback<CellId>() {
            @Override
            public void success(CellId cellId, Response response) {
                latitude = cellId.location.lat;
                longitude = cellId.location.lng;
                accuracy = cellId.accuracy;
                ipFlag = false;
                txtLatLng.setText(new StringBuilder().append("Lat, Lng = ").append(cellId.location.lat).append(", ").append(cellId.location.lng).toString());
                txtAcc.setText(new StringBuilder().append("Precisão = ").append(cellId.accuracy).toString());
                txtIpFlag.setText(new StringBuilder().append("IP_FLAG = ").append(ipFlag).toString());
            }
            @Override
            public void failure(RetrofitError error) {
                ipFlag = true;
                txtIpFlag.setText(new StringBuilder().append("IP_FLAG = ").append(ipFlag).toString());
                service.geolocate( cellIdRequestParam_considerIpTrue , "AIzaSyChKotrFZAIXnWtyzg6NOmuYONb3ASom7A", new Callback<CellId>() {
                    @Override
                    public void success(CellId cellId, Response response) {
                        latitude = cellId.location.lat;
                        longitude = cellId.location.lng;
                        accuracy = cellId.accuracy;
                        txtLatLng.setText(new StringBuilder().append("Lat, Lng = ").append(cellId.location.lat).append(", ").append(cellId.location.lng).toString());
                        txtAcc.setText(new StringBuilder().append("Precisão = ").append(cellId.accuracy).toString());
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage() + "\nURL: " + error.getUrl());
                    }
                });
            }
        });

        RestAdapter adapter2 = new RestAdapter.Builder()
                .setEndpoint("https://us1.unwiredlabs.com")
                .build();

        CellIdService2 service2 = adapter2.create(CellIdService2.class);
        service2.geolocate( new CellIdRequestParam2(
                    "pk.f94f86c6f75dbc00fbe0c2fa8932adc5",
                    "lte",
                    mcc,
                    mnc,
                new Cells[]{new Cells(lac, cid, pci,dbm)},
                    1),
                    new Callback<Location2>() {

            @Override
            public void success(Location2 location2, Response response) {
                status2 = location2.status;
                balance2 = location2.balance;
                latitude2 = location2.lat;
                longitude2 = location2.lon;
                accuracy2 = location2.accuracy;
                address2 = location2.address;
                message2 = location2.message;

                txtLatLng2.setText(new StringBuilder().append("Lat, Lng = ").append(location2.lat).append(", ").append(location2.lon).toString());
                txtAcc2.setText(new StringBuilder().append("Precisão = ").append(location2.accuracy).toString());
                txtAddress2.setText(new StringBuilder().append("Endereco: ").append(address2).toString());
                txtMessage2.setText(new StringBuilder().append("Mensagem: ").append(message2).toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage() + "\nURL: " + error.getUrl());
            }
        });
    }

    public void seeInMaps(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("accuracy", accuracy);
        intent.putExtra("latitude2", latitude2);
        intent.putExtra("longitude2", longitude2);
        intent.putExtra("latitudeGps", locationGps.getLatitude());
        intent.putExtra("longitudeGps", locationGps.getLongitude());
        startActivity(intent);
    }
}
