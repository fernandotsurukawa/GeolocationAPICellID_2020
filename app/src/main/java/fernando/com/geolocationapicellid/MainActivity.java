package fernando.com.geolocationapicellid;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.CellIdentityLte;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private int dbm;
    private TextView txtLatLng;
    private TextView txtAcc;
    private int lac;
    private int cid;
    private int mcc;
    private int mnc;
    private String macAddress;
    private String networkOperator;
    private double latitude;
    private double longitude;
    private double accuracy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtOperator = (TextView) findViewById(R.id.txtOperator);
        TextView txtLac = (TextView) findViewById(R.id.txtLac);
        TextView txtMnc = (TextView) findViewById(R.id.txtMnc);
        TextView txtMcc = (TextView) findViewById(R.id.txtMcc);
        TextView txtCid = (TextView) findViewById(R.id.txtCid);
        TextView txtSNR = (TextView) findViewById(R.id.txtSNR);
        TextView txtMacAddress = (TextView) findViewById(R.id.txtMacAddress);
        TextView txtSSID = (TextView) findViewById(R.id.txtSSID);
        txtLatLng = (TextView) findViewById(R.id.txtLatLng);
        txtAcc = (TextView) findViewById(R.id.txtAcc);
        final TelephonyManager t = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        assert t != null;
        if (t.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) { // Essa verificacao eh vestigial, nao serve pra nada
            final List<CellInfo> cellInfoList = t.getAllCellInfo();
            if (cellInfoList != null) {
                Log.e("CELL INFO DEBUGGING", String.valueOf(cellInfoList));
                CellInfoLte cellInfoLte = (CellInfoLte) cellInfoList.get(0);
                CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();
                cid = cellIdentityLte.getCi();
                lac = cellIdentityLte.getTac();
                mcc = cellIdentityLte.getMcc();
                mnc = cellIdentityLte.getMnc();
                networkOperator = t.getSimOperatorName();
                dbm = cellInfoLte.getCellSignalStrength().getDbm();

                txtOperator.setText(new StringBuilder().append("Nome da operadora: ").append(networkOperator).toString());
                txtCid.setText(new StringBuilder().append("Cell ID (CID): ").append(cid).toString());
                txtLac.setText(new StringBuilder().append("Location Area Code (LAC): ").append(lac).toString());
                txtMcc.setText(new StringBuilder().append("Mobile Country Code (MCC): ").append(mcc).toString());//mcc
                txtMnc.setText(new StringBuilder().append("Mobile Network Code (MNC): ").append(mnc).toString());//mnc
                txtSNR.setText(new StringBuilder().append("Potencia do Sinal (em dBm): ").append(dbm).toString());//mnc

                /*lac = location.getLac();
                cid = location.getCid();
                txtLac.setText(new StringBuilder().append("Location Area Code (LAC): ").append(lac).toString());
                txtCid.setText(new StringBuilder().append("Cell ID (CID): ").append(cid).toString());
                networkOperator = t.getSimOperatorName();
                MCCMNC = t.getSimOperator();
                MCC = String.valueOf(Integer.parseInt(MCCMNC.substring(0,3)));
                MNC = String.valueOf(Integer.parseInt(MCCMNC.substring(3)));
                txtOperator.setText(new StringBuilder().append("Nome da operadora: ").append(networkOperator).toString());
                txtMcc.setText(new StringBuilder().append("Mobile Country Code (MCC): ").append(MCC).toString());//mcc
                txtMnc.setText(new StringBuilder().append("Mobile Network Code (MNC): ").append(MNC).toString());//mnc*/
            }
        }

        WifiManager mainWifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo currentWifi = mainWifi.getConnectionInfo();
        if(currentWifi != null)
        {
            macAddress = getMacAddr().toLowerCase();
            txtSSID.setText(currentWifi.getSSID());
            txtMacAddress.setText(macAddress);
        }
    }

    public void getPositionByCellId(View view){
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("https://www.googleapis.com")
                .build();

        CellIdService service = adapter.create(CellIdService.class);
        service.geolocate( new CellIdRequestParam(
                "lte",
                true,
                new CellTowers(String.valueOf(cid), String.valueOf(lac), String.valueOf(mcc), String.valueOf(mnc))
        ), "AIzaSyChKotrFZAIXnWtyzg6NOmuYONb3ASom7A", new Callback<CellId>() {

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
                Log.e("TESTE", "ERRO: " + error.getMessage() + "\nURL: " + error.getUrl());
            }
        });
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    public void getPositionByWiFi(View view){
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("https://www.googleapis.com")
                .build();

        WifiService service = retrofit.create(WifiService.class);
        service.geolocate(new WifiRequestParam(macAddress),
                "AIzaSyChKotrFZAIXnWtyzg6NOmuYONb3ASom7A",
                new Callback<CellId>()
                {

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
                Log.e("TESTE", "ERRO: " + error.getMessage() + "\nURL: " + error.getUrl());
            }
        });
    }

    public void seeInMaps(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("accuracy", accuracy);
        startActivity(intent);
    }
}
