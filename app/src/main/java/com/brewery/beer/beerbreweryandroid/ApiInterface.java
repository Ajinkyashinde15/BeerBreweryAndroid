package com.brewery.beer.beerbreweryandroid;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Ajinkya
 */


public interface ApiInterface {
    @GET("beers")
    Call<BeersResponse> getallBeeers(@Query("api_key") String apiKey);
}
