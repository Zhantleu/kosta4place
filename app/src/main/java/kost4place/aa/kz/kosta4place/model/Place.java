package kost4place.aa.kz.kosta4place.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "places",
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = ForeignKey.CASCADE))
public class Place {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "placeId")
    @SerializedName("id")
    private Integer id;

    @ColumnInfo(name = "place_title")
    @SerializedName("placeTitle")
    private String placeTitle;

    @ColumnInfo(name = "info")
    @SerializedName("info")
    private String info;

    @ColumnInfo(name = "location")
    @SerializedName("location")
    private String location;

    @ColumnInfo(name = "urlLocation")
    @SerializedName("urlLocation")
    private String urlLocation;

    @Ignore
    @SerializedName("category")
    @Expose
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", placeTitle='" + placeTitle + '\'' +
                ", info='" + info + '\'' +
                ", location='" + location + '\'' +
                ", urlLocation='" + urlLocation + '\'' +
                '}';
    }
}

