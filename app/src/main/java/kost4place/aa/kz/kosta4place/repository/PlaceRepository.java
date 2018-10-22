package kost4place.aa.kz.kosta4place.repository;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.remote.api.KostaApi;
import kost4place.aa.kz.kosta4place.local.data.PlaceORM;
import kost4place.aa.kz.kosta4place.model.Place;
import kost4place.aa.kz.kosta4place.remote.service.KostaServiceRetrofit;
import retrofit2.Retrofit;

public class PlaceRepository {
    private KostaApi kostaApi;

    public Observable<List<Place>> getPlaces(Context context) {
        //Init
        Retrofit retrofit = KostaServiceRetrofit.getInstance();
        kostaApi = retrofit.create(KostaApi.class);

        return Observable.concatArray(
                getPlacesFromApi(context),
                getPlacesFromDb(context));
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
                    Log.d("from api", String.valueOf(places.size()));
                    storePlacesInDb(places, context);
                    Log.d("from api to db", String.valueOf(places.size()));
                });
    }

    @SuppressLint("CheckResult")
    private void storePlacesInDb(List<Place> places, Context context) {
        Observable.fromCallable(() -> PlaceORM.getInstance(context).insertAll(places))
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe();
    }
}
