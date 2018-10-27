package kost4place.aa.kz.kosta4place.repository;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.local.data.PlaceORM;
import kost4place.aa.kz.kosta4place.model.Place;
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

    public Observable<List<Place>> getPlaces(Context context) {
        return Observable.mergeDelayError(
                getPlacesFromApi(context)
                        .doOnNext(places -> PlaceORM.getInstance(context).insert(places)).subscribeOn(Schedulers.io()),
                getPlacesFromDb(context).subscribeOn(Schedulers.io()));

    }

    private Observable<List<Place>> getPlacesFromDb(Context context) {
        return PlaceORM.getInstance(context).getAll()
                .filter(places -> !places.isEmpty())
                .toObservable()
                .doOnNext(places -> Log.d("from cache", String.valueOf(places.size())));
    }

    private Observable<List<Place>> getPlacesFromApi(Context context) {
        return kostaApi.getPlace()
                .doOnNext(places -> {
                    storePlacesInDb(places, context);
                });
    }

    @SuppressLint("CheckResult")
    private void storePlacesInDb(List<Place> places, Context context) {
        Observable.fromCallable(() -> PlaceORM.getInstance(context).insertAll(places))
                .observeOn(Schedulers.io())
                .subscribe();
    }
}
