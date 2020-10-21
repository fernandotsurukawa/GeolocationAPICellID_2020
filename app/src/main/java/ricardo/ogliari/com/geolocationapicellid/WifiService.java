package ricardo.ogliari.com.geolocationapicellid;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

public interface WifiService {
    @Headers({"Accept: application/json"})
    @POST("/geolocation/v1/geolocate")
    void geolocate
            (@Body WifiRequestParam body,
             @Query("key") String key,
             Callback<CellId> callback);
}

