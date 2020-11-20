package fernando.com.geolocationapicellid;

public class CellTowers {
    int cellId;
    int locationAreaCode;
    int mobileCountryCode;
    int mobileNetworkCode;
    int signalStrength;

    CellTowers(int cellId, int locationAreaCode, int mobileCountryCode, int mobileNetworkCode, int signalStrength)
    {
        this.cellId = cellId;
        this.locationAreaCode = locationAreaCode;
        this.mobileCountryCode = mobileCountryCode;
        this.mobileNetworkCode = mobileNetworkCode;
        this.signalStrength = signalStrength;
    }
}
