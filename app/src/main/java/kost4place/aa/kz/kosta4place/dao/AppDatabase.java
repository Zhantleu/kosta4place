package kost4place.aa.kz.kosta4place.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kost4place.aa.kz.kosta4place.model.Place;

@Database(entities = {Place.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PlaceDao placeDao();
}
