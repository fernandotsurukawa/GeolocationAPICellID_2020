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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private TextView txtLatLng;
    private TextView txtAcc;
    private int lac;
    private int cid;
    private int mcc;
    private int mnc;
    private int dbm;
    private String macAddress;
    private double latitude;
    private double longitude;
    private double accuracy;
    private List<Integer> cidList = new ArrayList<>();
    private List<Integer> lacList = new ArrayList<>();
    private List<Integer> mccList = new ArrayList<>();
    private List<Integer> mncList = new ArrayList<>();
    private List<Integer> dbmList = new ArrayList<>();

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

                String networkOperator = t.getSimOperatorName();

                List<CellInfoLte> cellInfoLteList = (List<CellInfoLte>)(Object)cellInfoList;

                for(CellInfoLte cellInfoLte: cellInfoLteList) {
                    CellIdentityLte cellIdentityLte = cellInfoLte.getCellIdentity();

                    if(cellIdentityLte.getCi() != UNAVAILABLE) { //informacoes so sao compartilhadas se a torre eh compativel com a operadora
                        cidList.add(cellIdentityLte.getCi());
                        lacList.add(cellIdentityLte.getTac());
                        mccList.add(cellIdentityLte.getMcc());
                        mncList.add(cellIdentityLte.getMnc());
                        dbmList.add(cellInfoLte.getCellSignalStrength().getDbm()); //informacoes de potencia sao fornecidas sempre
                    }
                    /*else{
                        cidList.add(0);
                        lacList.add(0);
                        mccList.add(0);
                        mncList.add(0);
                    }*/
                    //dbmList.add(cellInfoLte.getCellSignalStrength().getDbm()); //informacoes de potencia sao fornecidas sempre
                }

                txtOperator.setText(new StringBuilder().append("Nome da operadora: ").append(networkOperator).toString());

                /*txtCid.setText(new StringBuilder().append("Cell ID (CID): ").append(Arrays.toString(cidList.toArray())));
                txtLac.setText(new StringBuilder().append("Location Area Code (LAC): ").append(Arrays.toString(lacList.toArray())));
                txtMcc.setText(new StringBuilder().append("Mobile Country Code (MCC): ").append(Arrays.toString(mccList.toArray())));
                txtMnc.setText(new StringBuilder().append("Mobile Network Code (MNC): ").append(Arrays.toString(mncList.toArray())));
                txtDbm.setText(new StringBuilder().append("Potencia do Sinal (em dBm): ").append(Arrays.toString(dbmList.toArray())));*/

                cid = cidList.get(0);
                lac = lacList.get(0);
                mcc = mccList.get(0);
                mnc = mncList.get(0);
                dbm = dbmList.get(0);

                txtCid.setText(new StringBuilder().append("Cell ID (CID): ").append(cid).toString());
                txtLac.setText(new StringBuilder().append("Location Area Code (LAC): ").append(lac).toString());
                txtMcc.setText(new StringBuilder().append("Mobile Country Code (MCC): ").append(mcc).toString());
                txtMnc.setText(new StringBuilder().append("Mobile Network Code (MNC): ").append(mnc).toString());
                txtDbm.setText(new StringBuilder().append("Mobile Network Code (MNC): ").append(dbm).toString());
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
        service.geolocate(new WifiRequestParam(
                true,
                        macAddress),
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
