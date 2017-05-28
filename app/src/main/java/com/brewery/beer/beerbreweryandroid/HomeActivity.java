package com.brewery.beer.beerbreweryandroid;
/**
 * Created by Ajinkya
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    //Api url= https://api.brewerydb.com/v2/beer/random?key=485d537596b0197fe767a7ed5dc562fe;
    private final static String API_KEY = "485d537596b0197fe767a7ed5dc562fe";
    Button nextButton;
    TextView beerTitle;
    ImageView beerImageView;
    MultiAutoCompleteTextView beerDescription;
    private static final String TAG = HomeActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e)
        {

        }
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from brewerydb.com", Toast.LENGTH_LONG).show();
            return;
        }

        if(!haveNetworkConnection())
        {
            Toast.makeText(getApplicationContext(), "Please Turn youe Internet connection on...!!!", Toast.LENGTH_LONG).show();
        }
        nextButton=(Button)findViewById(R.id.nextButton);
        beerTitle=(TextView)findViewById(R.id.beerTitle);
        beerImageView=(ImageView)findViewById(R.id.beerImageView);
        beerDescription=(MultiAutoCompleteTextView)findViewById(R.id.beerDescription);
        printRandombeer();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printRandombeer();
            }
        });

    }

    void printRandombeer()
    {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        try {
            Call<BeersResponse> call = apiService.getrandomBeer(API_KEY);

            call.enqueue(new Callback<BeersResponse>() {
                @Override
                public void onResponse(Call<BeersResponse> call, Response<BeersResponse> response) {
                    BeerDB beer  = response.body().getData();
                    beerTitle.setText(beer.getName());
                    beerDescription.setText(beer.getDescription());
                    try {
                        String str = beer.getLabels().getIcon();
                        if (!(str == null)) {

                            URL newurl = new URL(beer.getLabels().getIcon());
                            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                            beerImageView.setImageBitmap(mIcon_val);
                          //  Uri myUri = Uri.parse(beer.getLabels().getIcon());
                            //beerImageView.setImageURI(myUri);
                        } else {
                            Toast.makeText(getApplicationContext(), "Image label not available for " + beer.getName() + " beer", Toast.LENGTH_LONG).show();
                        }//Log.d(TAG , "Number of movies received: " + beer.getLabels());
                    }catch (Exception e)
                    {
                        beerImageView.setImageResource(R.mipmap.ic_launcher);
                        Toast.makeText(getApplicationContext(), "Image label not available for " + beer.getName() + " beer", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<BeersResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
            if(beerDescription.getText().toString().equals(""))
            {
                beerDescription.setText("No description to display");
            }
        }
        catch(Exception e)
        {
            String a=e.getMessage();
            e.printStackTrace();
        }

    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
}
