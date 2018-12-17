package kost4place.aa.kz.kosta4place.remote.api;

import java.util.List;

import io.reactivex.Observable;
import kost4place.aa.kz.kosta4place.model.Place;
import kost4place.aa.kz.kosta4place.model.PlaceWithCategory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface KostaApi {
    @GET("all")
    Observable<List<Place>> getAllPlaces();

    @GET("category/{category}")
    Observable<List<PlaceWithCategory>> getPlacesByCategory(@Path("category") String category);

    @GET("place/{id}")
    Observable<Place> getPlaceById(@Path("id") Long id);

}
