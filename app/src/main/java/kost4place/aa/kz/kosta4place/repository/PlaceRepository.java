package kost4place.aa.kz.kosta4place.repository;


import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.local.data.PlaceORM;
import kost4place.aa.kz.kosta4place.local.model.LocalPlace;
import kost4place.aa.kz.kosta4place.local.model.PlaceWithCategory;
import kost4place.aa.kz.kosta4place.remote.api.KostaApi;
import kost4place.aa.kz.kosta4place.remote.service.ServiceRetrofit;
import kost4place.aa.kz.kosta4place.utils.ConvertUtils;
import retrofit2.Retrofit;

public class PlaceRepository {
    private KostaApi kostaApi;

    private static final String TAG = "PlaceRepository";

    public PlaceRepository() {
        //Init
        Retrofit retrofit = ServiceRetrofit.getInstance();
        kostaApi = retrofit.create(KostaApi.class);
    }

    public Observable<List<LocalPlace>> getPlaces(Context context, String categoryId) {
        return Observable.mergeDelayError(

                getPlacesFromApi(categoryId)
                        .doOnNext(placeWithCategories -> PlaceORM.getInstance(context).insertAll(placeWithCategories))
                        .subscribeOn(Schedulers.io()),

                getPlacesFromDb(context, categoryId)
                        .filter(localPlaces -> !localPlaces.isEmpty())
                        .subscribeOn(Schedulers.io()))
                .map(ConvertUtils::returnLocalFromPlaceWithCategory);
    }

    private Observable<List<PlaceWithCategory>> getPlacesFromDb(Context context, String categoryId) {
        return PlaceORM.getInstance(context).getPlaceByCategoryId(categoryId)
                .filter(localPlaces -> !localPlaces.isEmpty())
                .toObservable()
                .map(ConvertUtils::returnPlaceWithCategory);
    }

    private Observable<List<PlaceWithCategory>> getPlacesFromApi(String category) {
        return kostaApi.getPlacesByCategory(category)
                .map(ConvertUtils::convert);
    }
}