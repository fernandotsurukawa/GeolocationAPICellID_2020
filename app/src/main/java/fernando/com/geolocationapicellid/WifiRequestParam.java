package fernando.com.geolocationapicellid;

public class WifiRequestParam {
    String macAddress;
    Boolean considerIp;

    WifiRequestParam(Boolean considerIp, String macAddress)
    {
        this.considerIp = considerIp;
        this.macAddress = macAddress;
    }
}
