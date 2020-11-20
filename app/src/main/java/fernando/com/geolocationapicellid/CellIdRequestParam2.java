package fernando.com.geolocationapicellid;

public class CellIdRequestParam2 {
    String token; //pk.f94f86c6f75dbc00fbe0c2fa8932adc5",
    String radio; //: "lte",
    int mcc;//": 724,
    int mnc;//": 5,
    Cells[] cells; //":
    int address;//": 1

    CellIdRequestParam2(String token, String radio, int mcc, int mnc, Cells[] cells, int address)
    {
        this.token = token;
        this.radio = radio;
        this.mcc = mcc;
        this.mnc = mnc;
        this.cells = cells;
        this.address = address;
    }
}
