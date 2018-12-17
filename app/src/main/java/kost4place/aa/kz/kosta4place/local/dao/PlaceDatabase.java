package kost4place.aa.kz.kosta4place.local.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kost4place.aa.kz.kosta4place.model.Category;
import kost4place.aa.kz.kosta4place.model.Place;
import kost4place.aa.kz.kosta4place.model.PlaceWithCategory;

@Database(entities = {Place.class,Category.class}, version = 1,exportSchema = false)
public abstract class PlaceDatabase extends RoomDatabase {
    public abstract PlaceDao placeDao();
}
