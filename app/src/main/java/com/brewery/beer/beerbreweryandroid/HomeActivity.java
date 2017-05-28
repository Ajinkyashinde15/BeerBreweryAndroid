package com.brewery.beer.beerbreweryandroid;
/**
 * Created by Ajinkya
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    //Api url= https://api.brewerydb.com/v2/beer/oeGSxs?key=485d537596b0197fe767a7ed5dc562fe&format=json;
    private final static String API_KEY = "485d537596b0197fe767a7ed5dc562fe";
    Button nextButton;
    TextView beerTitle;
    ImageView beerImageView;
    MultiAutoCompleteTextView beerDescription;
    private static final String TAG = HomeActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }
        nextButton=(Button)findViewById(R.id.nextButton);
        beerTitle=(TextView)findViewById(R.id.beerTitle);
        beerImageView=(ImageView)findViewById(R.id.beerImageView);
        beerDescription=(MultiAutoCompleteTextView)findViewById(R.id.beerDescription);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        try {
//            Call<BeersResponse> call = apiService.getallBeers('API_KEY);
            Call<BeersResponse> call = apiService.getoneBeeer("oeGSxs",API_KEY);

            call.enqueue(new Callback<BeersResponse>() {
                @Override
                public void onResponse(Call<BeersResponse> call, Response<BeersResponse> response) {
                    BeerDB beer  = response.body().getData();
                    //Log.d(TAG + " Ajinkya", "Number of movies received: " + beer.getLabels());
                    Toast.makeText(getApplicationContext(), "Number of movies received: " + beer.getLabels().getIcon(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<BeersResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }
        catch(Exception e)
        {
            String a=e.getMessage();
            e.printStackTrace();
        }
    }
}
