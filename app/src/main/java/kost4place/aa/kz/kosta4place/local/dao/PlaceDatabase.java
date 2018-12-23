package kost4place.aa.kz.kosta4place.local.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kost4place.aa.kz.kosta4place.local.model.LocalCategory;
import kost4place.aa.kz.kosta4place.local.model.LocalPlace;

@Database(entities = {LocalPlace.class,LocalCategory.class}, version = 1,exportSchema = false)
public abstract class PlaceDatabase extends RoomDatabase {
    public abstract PlaceDao placeDao();
}
