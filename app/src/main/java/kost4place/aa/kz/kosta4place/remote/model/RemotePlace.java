package kost4place.aa.kz.kosta4place.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemotePlace {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("placeTitle")
    @Expose
    private String placeTitle;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("urlLocation")
    @Expose
    private String urlLocation;
    @SerializedName("category")
    @Expose
    private RemoteCategory category;

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

    public RemoteCategory getCategory() {
        return category;
    }

    public void setCategory(RemoteCategory category) {
        this.category = category;
    }

}
