package kost4place.aa.kz.kosta4place.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Relation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceWithCategory {
    @Expose(serialize = false, deserialize = false)
    @Embedded
    public Category category;

    @Ignore
    @SerializedName("Place")
    public Place place;

    @Expose(serialize = false, deserialize = false)
    @Relation(parentColumn = "id", entity = Place.class, entityColumn = "placeId")
    public List<Place> places;

    @Override
    public String toString() {
        return "PlaceWithCategory{" +
                "place=" + place.toString()+
                ", place=" + place.getCategory().toString() +
                '}';
    }
}
