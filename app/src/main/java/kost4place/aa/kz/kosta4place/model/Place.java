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

    public Place(String placeTitle, String info, String location) {
        this.placeTitle = placeTitle;
        this.info = info;
        this.location = location;
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
}

//    @PrimaryKey(autoGenerate = true)
//    @SerializedName("id")
//    @Expose
//    private Integer id;
//
//    @ColumnInfo(name = "place_title")
//    @SerializedName("placeTitle")
//    @Expose
//    private String placeTitle;
//
//    @ColumnInfo(name = "info")
//    @SerializedName("info")
//    @Expose
//    private String info;
//
//    @ColumnInfo(name = "location")
//    @SerializedName("location")
//    @Expose
//    private String location;
//
//    @SerializedName("category")
//    @Expose
//    private Category category;
//
//    public Place(String placeTitle, String info, String location) {
//        this.placeTitle = placeTitle;
//        this.info = info;
//        this.location = location;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getPlaceTitle() {
//        return placeTitle;
//    }
//
//    public void setPlaceTitle(String placeTitle) {
//        this.placeTitle = placeTitle;
//    }
//
//    public String getInfo() {
//        return info;
//    }
//
//    public void setInfo(String info) {
//        this.info = info;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//}
