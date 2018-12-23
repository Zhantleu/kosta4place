package kost4place.aa.kz.kosta4place.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RemoteCategory {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("categoryTitle")
    @Expose
    private String categoryTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
