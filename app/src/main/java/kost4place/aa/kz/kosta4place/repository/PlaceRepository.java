package kost4place.aa.kz.kosta4place.repository;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.local.data.PlaceORM;
import kost4place.aa.kz.kosta4place.model.PlaceWithCategory;
import kost4place.aa.kz.kosta4place.remote.api.KostaApi;
import kost4place.aa.kz.kosta4place.remote.service.ServiceRetrofit;
import retrofit2.Retrofit;

public class PlaceRepository {
    private KostaApi kostaApi;

    public PlaceRepository() {
        //Init
        Retrofit retrofit = ServiceRetrofit.getInstance();
        kostaApi = retrofit.create(KostaApi.class);
    }

    public Observable<List<PlaceWithCategory>> getPlaces(Context context,String category) {
        return Observable.mergeDelayError(
                getPlacesFromApi(context,category)
                        .doOnNext(places -> PlaceORM.getInstance(context).insert(places))
                        .subscribeOn(Schedulers.io()),
                getPlacesFromDb(context,category).subscribeOn(Schedulers.io()));

    }

    private Observable<List<PlaceWithCategory>> getPlacesFromDb(Context context, String category) {
        return PlaceORM.getInstance(context).getPlaceBy(category)
                .filter(places -> !places.isEmpty())
                .toObservable()
                .doOnNext(places -> Log.d("from cache", String.valueOf(places.size())));
    }

    private Observable<List<PlaceWithCategory>> getPlacesFromApi(Context context, String category) {
        return kostaApi.getPlacesByCategory(category)
                .doOnNext(places -> {
                    Log.d("from api", places.toString());
                    storePlacesInDb(places, context);
                });
    }

    @SuppressLint("CheckResult")
    private void storePlacesInDb(List<PlaceWithCategory> places, Context context) {
        Observable.fromCallable(() -> PlaceORM.getInstance(context).insertAll(places))
                .observeOn(Schedulers.io())
                .subscribe();
    }
}
