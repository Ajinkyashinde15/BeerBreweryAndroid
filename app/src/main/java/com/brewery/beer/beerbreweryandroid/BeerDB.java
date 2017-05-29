package com.brewery.beer.beerbreweryandroid;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ajinkya
 */

//Create and define attributes in JSON file that we want to extract
public class BeerDB
{
    //Define serialized variables name
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("labels")
    private IconClass icon;

    //Constructor definition
    public BeerDB(String name, String description, IconClass icon) {
        this.name = name;
        this.description = description;
        this.icon = icon;
    }

    //Getter and setter method for all variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IconClass getLabels() {
        return icon;
    }

   public void setLabels(IconClass icon) { this.icon = icon;}

}
