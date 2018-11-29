package kost4place.aa.kz.kosta4place.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "places")
public class Place {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "place_title")
    private String placeTitle;

    @ColumnInfo(name = "info")
    private String info;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "urlLocation")
    private String urlLocation;

    public Place(String placeTitle, String info, String location, String urlLocation) {
        this.placeTitle = placeTitle;
        this.info = info;
        this.location = location;
        this.urlLocation = urlLocation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrlLocation() {
        return urlLocation;
    }

    public void setUrlLocation(String urlLocation) {
        this.urlLocation = urlLocation;
    }
}

