package kost4place.aa.kz.kosta4place.api;

import java.util.List;

import kost4place.aa.kz.kosta4place.model.Place;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KostaService {
    @GET("/place/all")
    Call<List<Place>> getPlace(Query(""))
}
