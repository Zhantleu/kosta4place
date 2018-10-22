package kost4place.aa.kz.kosta4place.local.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import kost4place.aa.kz.kosta4place.local.dao.PlaceDao;
import kost4place.aa.kz.kosta4place.local.dao.PlaceDatabase;
import kost4place.aa.kz.kosta4place.model.Place;

public class PlaceORM implements PlaceDao {
    private static final String DB_NAME = "database-name";
    private Context context;
    private static PlaceORM _instance;
    private PlaceDatabase db;

    private PlaceORM(Context context) {
        this.context = context;
        db = Room.databaseBuilder(context, PlaceDatabase.class, DB_NAME)
                .build();
    }

    public static PlaceORM getInstance(Context context) {
        if (_instance == null) {
            _instance = new PlaceORM(context);
        }
        return _instance;
    }

    @Override
    public Flowable<List<Place>> getAll() {
        Flowable<List<Place>> flowable = db.placeDao().getAll();

        return flowable;
    }

    @Override
    public void insert(List<Place> place) {
        db.placeDao().insert(place);
    }

    public Completable insertAll(List<Place> place) {
        return Completable.fromAction(() -> {
            insert(place);
        });
    }
}
