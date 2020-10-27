package fernando.com.geolocationapicellid;

public class CellIdRequestParam {
    String radioType;
    CellTowers cellTower;

    CellIdRequestParam(String radioType, CellTowers cellTower)
    {
        this.radioType = radioType;
        this.cellTower = cellTower;
    }
}
