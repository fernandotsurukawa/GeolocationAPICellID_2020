package fernando.com.geolocationapicellid;

public class CellIdRequestParam {
    String radioType;
    Boolean considerIp;
    CellTowers cellTower;

    CellIdRequestParam(String radioType, Boolean considerIp, CellTowers cellTower)
    {
        this.radioType = radioType;
        this.considerIp = considerIp;
        this.cellTower = cellTower;
    }
}
