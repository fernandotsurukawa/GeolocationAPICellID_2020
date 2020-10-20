package ricardo.ogliari.com.geolocationapicellid;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtLatLng;

    private int lac;
    private int cid;
    private String networkOperator;
    private String MCCMNC;
    private String MCC;
    private String MNC;
    private String macAddress;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtOperator = (TextView) findViewById(R.id.txtOperator);
        TextView txtLac = (TextView) findViewById(R.id.txtLac);
        TextView txtMnc = (TextView) findViewById(R.id.txtMnc);
        TextView txtMcc = (TextView) findViewById(R.id.txtMcc);
        TextView txtCid = (TextView) findViewById(R.id.txtCid);
        TextView txtMacAddress = (TextView) findViewById(R.id.txtMacAddress);
        TextView txtSSID = (TextView) findViewById(R.id.txtSSID);
        txtLatLng = (TextView) findViewById(R.id.txtLatLng);

        final TelephonyManager t = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        assert t != null;
        if (t.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
            final GsmCellLocation location = (GsmCellLocation) t.getCellLocation();
            if (location != null) {
                lac = location.getLac();
                cid = location.getCid();

                txtLac.setText(new StringBuilder().append("Location Area Code (LAC): ").append(lac).toString());
                txtCid.setText(new StringBuilder().append("Cell ID (CID): ").append(cid).toString());
                networkOperator = t.getSimOperatorName();
                MCCMNC = t.getSimOperator();
                MCC = String.valueOf(Integer.parseInt(MCCMNC.substring(0,3)));
                MNC = String.valueOf(Integer.parseInt(MCCMNC.substring(3)));
                txtOperator.setText(new StringBuilder().append("Nome da operadora: ").append(networkOperator).toString());
                txtMcc.setText(new StringBuilder().append("Mobile Country Code (MCC): ").append(MCC).toString());//mcc
                txtMnc.setText(new StringBuilder().append("Mobile Network Code (MNC): ").append(MNC).toString());//mnc
                //Log.e("DEBUGGING", "MCCMNC = " + MCCMNC);
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
        RestAdapter retrofit = new RestAdapter.Builder()
                .setEndpoint("https://www.googleapis.com")
                .setClient(new OkClient(new OkHttpClient()))
                .build();

        CellIdService service = retrofit.create(CellIdService.class);
        service.geolocate(
                "{\n" +
                "\"radioType\": \"wcdma\"\n" +
                "  \"cellTowers\": [\n" +
                "    {\n" +
                "      \"cellId\": "+cid+",\n" +
                "      \"locationAreaCode\": "+lac+",\n" +
                "      \"mobileCountryCode\": "+MCC+",\n" +
                "      \"mobileNetworkCode\": "+MNC+"\n" +
                "    }\n" +
                "  ]\n" +
                "}", "AIzaSyChKotrFZAIXnWtyzg6NOmuYONb3ASom7A", new Callback<CellId>() {


            @Override
            public void success(CellId cellId, Response response) {
                txtLatLng.setText(new StringBuilder().append(cellId.location.lat).append(", ").append(cellId.location.lng).toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TESTE", "ERRO: " + error.getMessage());
            }
        });
        Log.e("DEBUGGING", "{\n" +
                "\"radioType\": \"wcdma\"\n" +
                "  \"cellTowers\": [\n" +
                "    {\n" +
                "      \"cellId\": "+cid+",\n" +
                "      \"locationAreaCode\": "+lac+",\n" +
                "      \"mobileCountryCode\": "+MCC+",\n" +
                "      \"mobileNetworkCode\": "+MNC+"\n" +
                "    }\n" +
                "  ]\n" +
                "}"
        );
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

        CellIdService service = retrofit.create(CellIdService.class);
        service.geolocate("{\n" + "  \"macAddress\": " + macAddress + "}", "AIzaSyChKotrFZAIXnWtyzg6NOmuYONb3ASom7A", new Callback<CellId>() {

            @Override
            public void success(CellId cellId, Response response) {
                latitude = cellId.location.lat;
                longitude = cellId.location.lng;
                txtLatLng.setText(new StringBuilder().append(latitude).append(", ").append(longitude).toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TESTE", "ERRO: " + error.getMessage());
            }
        });
        Log.e( "DEBUGGING", "{\n" + "  \"macAddress\": " + macAddress + "}");
    }

    public void seeInMaps(View v){
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }
}
