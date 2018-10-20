package kost4place.aa.kz.kosta4place.cache;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kost4place.aa.kz.kosta4place.dao.AppDatabase;
import kost4place.aa.kz.kosta4place.dao.DatabaseCallback;
import kost4place.aa.kz.kosta4place.model.Place;

public class LocalCacheManager {
    private static final String DB_NAME = "database-name";
    private Context context;
    private static LocalCacheManager _instance;
    private AppDatabase db;

    public LocalCacheManager(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public static LocalCacheManager getInstance(Context context) {
        if (_instance == null) {
            _instance = new LocalCacheManager(context);
        }
        return _instance;
    }

    public void getPlaces(final DatabaseCallback databaseCallback) {
        db.placeDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Place>>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull List<Place> places) {
                databaseCallback.onPlacesLoaded(places);
            }
        });
    }

    public void addUser(final DatabaseCallback databaseCallback, final String placeTitle, final String info,
                        String location) {
        Completable.fromAction(() -> {
            Place place = new Place(placeTitle, info, location);
            db.placeDao().insertAll(place);
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onComplete() {
                databaseCallback.onPlaceAdded(); // added method places
            }

            @Override
            public void onError(Throwable e) {
                databaseCallback.onDataNotAvailable();
            }
        });
    }
}
