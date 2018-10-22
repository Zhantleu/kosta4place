package kost4place.aa.kz.kosta4place.remote.api;

import java.util.List;

import io.reactivex.Observable;
import kost4place.aa.kz.kosta4place.model.Place;
import retrofit2.http.GET;

public interface KostaApi {
    @GET("place/all")
    Observable<List<Place>> getPlace();
}
