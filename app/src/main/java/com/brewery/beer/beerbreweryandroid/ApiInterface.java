package com.brewery.beer.beerbreweryandroid;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Ajinkya
 */


public interface ApiInterface {
    //@GET("beers")
    //Call<BeersResponse> getallBeeers(@Query("key") String apiKey);

    @GET("beer/{id}")
    Call<BeersResponse> getoneBeeer(@Path("id") String id, @Query("key") String apiKey);

}
