package fernando.com.geolocationapicellid;

public class CellIdRequestParam2 {
    String token; //pk.f94f86c6f75dbc00fbe0c2fa8932adc5",
    String radio; //: "lte",
    String mcc;//": 724,
    String mnc;//": 5,
    CellTowers2 cells; //":
    String address;//": 1

    CellIdRequestParam2(String token, String radio, String mcc, String mnc, CellTowers2 cells, String address)
    {
        this.token = token;
        this.radio = radio;
        this.mcc = mcc;
        this.mnc = mnc;
        this.cells = cells;
        this.address = address;
    }
}
