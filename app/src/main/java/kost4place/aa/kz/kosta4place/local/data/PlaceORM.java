package kost4place.aa.kz.kosta4place.local.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import kost4place.aa.kz.kosta4place.local.dao.PlaceDao;
import kost4place.aa.kz.kosta4place.local.dao.PlaceDatabase;
import kost4place.aa.kz.kosta4place.local.model.LocalCategory;
import kost4place.aa.kz.kosta4place.local.model.LocalPlace;
import kost4place.aa.kz.kosta4place.local.model.PlaceWithCategory;

public class PlaceORM extends PlaceDao {
    private static final String DB_NAME = "database-name";
    private static PlaceORM _instance;
    private PlaceDatabase db;
    private static final String TAG = "PlaceORM";

    private PlaceORM(Context context) {
        db = Room.databaseBuilder(context, PlaceDatabase.class, DB_NAME)
                .build();
    }

    public static PlaceORM getInstance(Context context) {
        if (_instance == null) {
            _instance = new PlaceORM(context);
        }
        return _instance;
    }

    public Disposable insertAll(List<PlaceWithCategory> placeWithCategories) {
        return Completable.fromAction(() -> {

            for (PlaceWithCategory placeWithCategory : placeWithCategories) {
                insertLocalCategory(placeWithCategory.localCategory);
                insertLocalPlace(placeWithCategory.localPlaces);
            }

        }).subscribe();
    }

    @Override
    public Flowable<List<LocalPlace>> getPlaceByCategoryId(String category) {
        return db.placeDao().getPlaceByCategoryId(category);
    }

    @Override
    public void insertLocalPlace(List<LocalPlace> localPlace) {
        db.placeDao().insertLocalPlace(localPlace);
    }

    @Override
    public void insertLocalCategory(LocalCategory localCategory) {
        db.placeDao().insertLocalCategory(localCategory);
    }
}
