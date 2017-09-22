package model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by bruno.bertelli on 05/04/2017.
 */

public class Repository {

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("language")
    public String language;

    @SerializedName("updated_at")
    public Date updatedAt;
}
