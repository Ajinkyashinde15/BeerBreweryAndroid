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

    //Call REST Api path beer/random and get random beer information
    @GET("beer/random")
    Call<BeersResponse> getrandomBeer(@Query("key") String apiKey);

}
