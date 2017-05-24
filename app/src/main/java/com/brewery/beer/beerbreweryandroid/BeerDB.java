package com.brewery.beer.beerbreweryandroid;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajinkya
 */

public class BeerDB
{
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private boolean description;
    @SerializedName("labels")
    private String labels;

    public BeerDB(String name, boolean description, String labels) {
        this.name = name;
        this.description = description;
        this.labels = labels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDescription() {
        return description;
    }

    public void setDescription(boolean description) {
        this.description = description;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
