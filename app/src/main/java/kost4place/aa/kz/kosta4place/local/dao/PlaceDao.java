package kost4place.aa.kz.kosta4place.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import kost4place.aa.kz.kosta4place.local.model.LocalCategory;
import kost4place.aa.kz.kosta4place.local.model.LocalPlace;

@Dao
public abstract class PlaceDao {
    private static final String TAG = "PlaceDao";


    @Query("SELECT places.* FROM places INNER JOIN categories ON categories.id =:categoryId")
    public abstract Flowable<List<LocalPlace>> getPlaceByCategoryId(String categoryId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertLocalPlace(List<LocalPlace> localPlace);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertLocalCategory(LocalCategory localCategory);

}
