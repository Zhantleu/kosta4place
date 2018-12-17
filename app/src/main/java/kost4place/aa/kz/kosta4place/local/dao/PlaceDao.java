package kost4place.aa.kz.kosta4place.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

import io.reactivex.Flowable;

import io.reactivex.Maybe;
import kost4place.aa.kz.kosta4place.model.Category;
import kost4place.aa.kz.kosta4place.model.Place;
import kost4place.aa.kz.kosta4place.model.PlaceWithCategory;

@Dao
public abstract class PlaceDao {
    @Transaction @Query("SELECT * FROM categories WHERE id =:placeId")
    public abstract Flowable<List<PlaceWithCategory>> getPlaceBy(String placeId);

    @Transaction
    public void insert(List<PlaceWithCategory> placeWithCategories) {
        for (PlaceWithCategory placeWithCategory : placeWithCategories) {
            insert(placeWithCategory.category);
            for (Place place : placeWithCategory.places) {
                insert(place);
            }
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(Place place);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(Category category);

}
