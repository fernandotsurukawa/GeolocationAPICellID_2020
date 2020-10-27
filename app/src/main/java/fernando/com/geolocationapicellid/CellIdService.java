package fernando.com.geolocationapicellid;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface CellIdService {
    @Headers({"Accept: application/json"})
    @POST("/geolocation/v1/geolocate")
    void geolocate
            (@Body CellIdRequestParam body,
             @Query("key") String key,
             Callback<CellId> callback);
}
