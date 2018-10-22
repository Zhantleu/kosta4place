package kost4place.aa.kz.kosta4place.local.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kost4place.aa.kz.kosta4place.model.Place;

@Database(entities = {Place.class}, version = 1,exportSchema = false)
public abstract class PlaceDatabase extends RoomDatabase {
    public abstract PlaceDao placeDao();
}
