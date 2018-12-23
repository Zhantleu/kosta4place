package kost4place.aa.kz.kosta4place.repository;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.local.data.PlaceORM;
import kost4place.aa.kz.kosta4place.local.model.LocalCategory;
import kost4place.aa.kz.kosta4place.local.model.LocalPlace;
import kost4place.aa.kz.kosta4place.local.model.PlaceWithCategory;
import kost4place.aa.kz.kosta4place.remote.api.KostaApi;
import kost4place.aa.kz.kosta4place.remote.service.ServiceRetrofit;
import kost4place.aa.kz.kosta4place.utils.ConvertUtils;
import retrofit2.Retrofit;

public class PlaceRepository {
    private KostaApi kostaApi;
    //Test
    List<PlaceWithCategory> placess = new LinkedList<>();
    List<LocalPlace> localPlaces = new LinkedList<>();

    private static final String TAG = "PlaceRepository";

    public PlaceRepository() {
        //Init
        Retrofit retrofit = ServiceRetrofit.getInstance();
        kostaApi = retrofit.create(KostaApi.class);


        //Init Test
        localPlaces.add(new LocalPlace(1, 1, "kafeshka", "asdasd", "asdasd", "asdasd"));
        placess.add(new PlaceWithCategory(new LocalCategory(1, "cafe"), localPlaces));
    }

    public Observable<List<PlaceWithCategory>> getPlaces(Context context, String category) {
        return Observable.merge(
                getPlacesFromApi(category)
                        .doOnNext(placeWithCategories -> {
                            Log.d(TAG, "getPlacesFromApiTwo: " + placeWithCategories.toString());
                            storePlacesInDb(placeWithCategories, context);
                        })
                        .subscribeOn(Schedulers.io()),
                getPlacesFromDb(context, category).subscribeOn(Schedulers.io()));
    }

    private Observable<List<PlaceWithCategory>> getPlacesFromDb(Context context, String category) {
        return PlaceORM.getInstance(context).getPlaceBy(category)
                .filter(places -> !places.isEmpty())
                .toObservable()
                .doOnNext(places -> Log.d("from cache", String.valueOf(places.size())))
                .map(ConvertUtils::returnPlaceWithCategory);
    }

    private Observable<List<PlaceWithCategory>> getPlacesFromApi(String category) {
        return kostaApi.getPlacesByCategory(category)
                .doOnNext(places -> Log.d("from api", places.toString()))
                .map(ConvertUtils::convert);
    }

    @SuppressLint("CheckResult")
    private void storePlacesInDb(List<PlaceWithCategory> places, Context context) {
        Log.d(TAG, "storePlacesInDb: " + places.toString());
        Observable.fromCallable(() -> PlaceORM.getInstance(context).insertAll(places))
                .observeOn(Schedulers.io())
                .subscribe();
    }
}


/**
 *   public Observable<List<PlaceWithCategory>> getPlaces(Context context, String category) {
 *         return Observable.mergeDelayError(
 *                 getPlacesFromApi(category)
 *                         .doOnNext(placeWithCategories -> {
 *                             Log.d(TAG, "getPlacesFromApiTwo: " + placeWithCategories.toString());
 *                             storePlacesInDb(placeWithCategories, context);
 *                         })
 *                         .subscribeOn(Schedulers.io()),
 *                 getPlacesFromDb(context, category).subscribeOn(Schedulers.io()));
 *     }
 **/