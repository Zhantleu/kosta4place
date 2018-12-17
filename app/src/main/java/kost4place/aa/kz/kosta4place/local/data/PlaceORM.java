package kost4place.aa.kz.kosta4place.local.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import kost4place.aa.kz.kosta4place.local.dao.PlaceDao;
import kost4place.aa.kz.kosta4place.local.dao.PlaceDatabase;
import kost4place.aa.kz.kosta4place.model.Category;
import kost4place.aa.kz.kosta4place.model.Place;
import kost4place.aa.kz.kosta4place.model.PlaceWithCategory;

public class PlaceORM extends PlaceDao {
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

    public Completable insertAll(List<PlaceWithCategory> place) {
        return Completable.fromAction(() -> {
            insert(place);
        });
    }

    @Override
    public Flowable<List<PlaceWithCategory>> getPlaceBy(String category) {
        return db.placeDao().getPlaceBy(category);
    }

    @Override
    public void insert(List<PlaceWithCategory> place) {
        db.placeDao().insert(place);
    }

    @Override
    public void insert(Place place) {
        db.placeDao().insert(place);
    }

    @Override
    public void insert(Category category) {
        db.placeDao().insert(category);
    }


}
