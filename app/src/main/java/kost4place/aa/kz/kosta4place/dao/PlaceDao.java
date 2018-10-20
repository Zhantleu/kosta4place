package kost4place.aa.kz.kosta4place.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import kost4place.aa.kz.kosta4place.model.Place;
@Dao
public interface PlaceDao {
    @Query("SELECT * FROM places")
    Maybe<List<Place>> getAll();

    @Query("SELECT * FROM places WHERE id IN (:placeIds)")
    Flowable<List<Place>> loadAllByIds(int[] placeIds);

    //When the application will be a bigger then i need to change this

//    @Query("SELECT * FROM places WHERE place_title LIKE :first AND "
//            + "last_name LIKE :last LIMIT 1")
//    Place findByName(String first, String last);
//
//    @Query("SELECT * FROM users where uid = :id")
//    Maybe<Place> findById(int id);


    @Insert
    void insertAll(Place... places);

    @Delete
    void delete(Place Place);

    @Update
    void updatePlace(Place... places);

}
