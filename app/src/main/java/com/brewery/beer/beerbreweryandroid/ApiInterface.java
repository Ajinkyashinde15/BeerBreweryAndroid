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

public interface ApiInterface
{
    //This method is used for "POST"
    @FormUrlEncoded

    //This method is used for "GET"
    @GET("/beers")
    Call<ServerResponse> get(
            @Query("method") String method,
            @Query("username") String username,
            @Query("password") String password
    );
}

