package com.brewery.beer.beerbreweryandroid;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ajinkya
 */

class IconClass
{
    @SerializedName("icon")
    String icon;

    public IconClass(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
