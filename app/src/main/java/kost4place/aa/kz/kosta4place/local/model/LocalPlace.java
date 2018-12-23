package kost4place.aa.kz.kosta4place.local.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

//@Entity(tableName = "places",
//        foreignKeys = @ForeignKey(entity = LocalCategory.class,
//                parentColumns = "id",
//                childColumns = "placeId",
//                onDelete = ForeignKey.CASCADE))
@Entity(tableName = "places")
public class LocalPlace {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "placeId")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "categoryId")
    private Integer categoryId;

    @ColumnInfo(name = "place_title")
    private String placeTitle;

    @ColumnInfo(name = "info")
    private String info;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "urlLocation")
    private String urlLocation;

    public LocalPlace(Integer id, Integer categoryId, String placeTitle, String info, String location, String urlLocation) {
        this.id = id;
        this.categoryId = categoryId;
        this.placeTitle = placeTitle;
        this.info = info;
        this.location = location;
        this.urlLocation = urlLocation;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    @Override
    public String toString() {
        return "LocalPlace{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", placeTitle='" + placeTitle + '\'' +
                ", info='" + info + '\'' +
                ", location='" + location + '\'' +
                ", urlLocation='" + urlLocation + '\'' +
                '}';
    }
}

