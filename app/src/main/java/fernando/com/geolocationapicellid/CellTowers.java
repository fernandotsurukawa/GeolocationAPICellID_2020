package fernando.com.geolocationapicellid;

public class CellTowers {
    String cellID;
    String locationAreaCode;
    String mobileCountryCode;
    String mobileNetworkCode;

    CellTowers(String cellID, String locationAreaCode, String mobileCountryCode, String mobileNetworkCode)
    {
        this.cellID = cellID;
        this.locationAreaCode = locationAreaCode;
        this.mobileCountryCode = mobileCountryCode;
        this.mobileNetworkCode = mobileNetworkCode;
    }
}
