package fernando.com.geolocationapicellid;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

public interface CellIdService2 {
    @Headers({"Accept: application/json"})
    @POST("/v2/process.php")
    void geolocate
            (@Body CellIdRequestParam2 body,
             Callback<Location2> callback);
}
