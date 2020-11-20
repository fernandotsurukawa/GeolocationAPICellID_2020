package fernando.com.geolocationapicellid;

public class CellIdRequestParam {
    String radioType;
    Boolean considerIp;
    CellTowers[] cellTowers;

    CellIdRequestParam(String radioType, Boolean considerIp, CellTowers[] cellTowers)
    {
        this.radioType = radioType;
        this.considerIp = considerIp;
        this.cellTowers = cellTowers;
    }
}
