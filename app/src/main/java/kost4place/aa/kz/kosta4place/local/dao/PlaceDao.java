package kost4place.aa.kz.kosta4place.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

import io.reactivex.Maybe;
import kost4place.aa.kz.kosta4place.model.Place;

@Dao
public interface PlaceDao {
    @Query("SELECT * FROM places")
    Flowable<List<Place>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Place> place);

}
