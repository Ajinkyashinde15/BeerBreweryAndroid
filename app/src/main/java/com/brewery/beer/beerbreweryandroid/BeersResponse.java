package com.brewery.beer.beerbreweryandroid;

/**
 * Created by Ajinkya
 */

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BeersResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private BeerDB data;

    @SerializedName("status")
    private String status;

    public BeersResponse(String message, BeerDB data, String status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BeerDB getData() {
        return data;
    }

    public void setData(BeerDB data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
