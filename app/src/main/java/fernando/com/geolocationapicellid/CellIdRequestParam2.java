package fernando.com.geolocationapicellid;

public class CellIdRequestParam2 {
    String token;
    String radio;
    int mcc;
    int mnc;
    Cells[] cells;
    int address;

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
