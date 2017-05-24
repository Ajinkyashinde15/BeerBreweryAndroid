package com.brewery.beer.beerbreweryandroid;

/**
 * Created by Ajinkya
 */

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BeersResponse {

    @SerializedName("results")
    private List<BeerDB> results;

    public List<BeerDB> getResults() {
        return results;
    }

    public void setResults(List<BeerDB> results) {
        this.results = results;
    }

}
