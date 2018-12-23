package kost4place.aa.kz.kosta4place.local.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;

import java.util.List;

public class PlaceWithCategory {
    @Embedded
    public LocalCategory localCategory;

    @Relation(parentColumn = "categoryId", entityColumn = "id", entity = LocalPlace.class)
    public List<LocalPlace> localPlaces;

    public PlaceWithCategory() {

    }

    @Ignore
    public PlaceWithCategory(LocalCategory localCategory, List<LocalPlace> localPlaces) {
        this.localCategory = localCategory;
        this.localPlaces = localPlaces;
    }

    @Override
    public String toString() {
        return "PlaceWithCategory{" +
                "localCategory=" + localCategory +
                ", localPlaces=" + localPlaces +
                '}';
    }
}
