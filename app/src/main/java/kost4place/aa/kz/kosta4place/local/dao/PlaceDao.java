package kost4place.aa.kz.kosta4place.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.util.Log;

import java.util.List;

import io.reactivex.Flowable;

import kost4place.aa.kz.kosta4place.local.model.LocalCategory;
import kost4place.aa.kz.kosta4place.local.model.LocalPlace;
import kost4place.aa.kz.kosta4place.local.model.PlaceWithCategory;

@Dao
public abstract class PlaceDao {
    private static final String TAG = "PlaceDao";

    @Transaction
    @Query("SELECT * FROM places WHERE categoryId =:categoryId")
    public abstract Flowable<List<LocalPlace>> getPlaceBy(String categoryId);

//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertPlaceWithCategory(List<PlaceWithCategory> placeWithCategories) {
        Log.d(TAG, "insertPlaceWithCategory: " + placeWithCategories);
        for (PlaceWithCategory placeWithCategory : placeWithCategories) {
            insertLocalCategory(placeWithCategory.localCategory);
            insertLocalPlace(placeWithCategory.localPlaces);
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertLocalPlace(List<LocalPlace> localPlace);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertLocalCategory(LocalCategory localCategory);

}
