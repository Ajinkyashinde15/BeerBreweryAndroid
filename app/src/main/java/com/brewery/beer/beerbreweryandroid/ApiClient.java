package com.brewery.beer.beerbreweryandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ajinkya
 */

public class ApiClient  {

//    public static final String BASE_URL = "https://api.brewerydb.com/v2/beer/oeGSxs/";

    public static final String BASE_URL = "https://api.brewerydb.com/v2/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}