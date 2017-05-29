package com.brewery.beer.beerbreweryandroid;
/**
 * Created by Ajinkya
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import java.net.URL;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    //Api url= https://api.brewerydb.com/v2/beer/random?key=485d537596b0197fe767a7ed5dc562fe;
    //Initialize api ket for brewerydb
    private final static String API_KEY = "485d537596b0197fe767a7ed5dc562fe";

    //Define variables for widgets
    Button nextButton;
    TextView beerTitle;
    ImageView beerImageView;
    MultiAutoCompleteTextView beerDescription;
    private static final String TAG = HomeActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Allowing network permission
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        //Showing an error if API key is empty
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from brewerydb.com", Toast.LENGTH_LONG).show();
            return;
        }

        //Check for Internet connection is enable else showing toast
        if(!haveNetworkConnection())
        {
            Toast.makeText(getApplicationContext(), "Please Turn youe Internet connection on...!!!", Toast.LENGTH_LONG).show();
        }
        nextButton=(Button)findViewById(R.id.nextButton);
        beerTitle=(TextView)findViewById(R.id.beerTitle);
        beerImageView=(ImageView)findViewById(R.id.beerImageView);
        beerDescription=(MultiAutoCompleteTextView)findViewById(R.id.beerDescription);
        printRandombeer();

        //Define events happen when button being pressed
        ((Button)findViewById(R.id.nextButton)).setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //Color will change
                    case MotionEvent.ACTION_DOWN: {
                        Button view = (Button) v;
                        view.getBackground().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                        //Check for Internet connection is enable else showing toast
                        if(!haveNetworkConnection())
                        {
                            Toast.makeText(getApplicationContext(), "Please Turn your Internet connection on...!!!", Toast.LENGTH_LONG).show();
                        }
                        printRandombeer();
                    case MotionEvent.ACTION_CANCEL: {
                        Button view = (Button) v;
                        view.getBackground().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return true;
            }
        });

    }

    //Method to get random method from brewerydb API's
    void printRandombeer()
    {
        //Defining and declaration of apiService object
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        try {
            //call to rest api with api key
            Call<BeersResponse> call = apiService.getrandomBeer(API_KEY);

            call.enqueue(new Callback<BeersResponse>() {
                @Override
                public void onResponse(Call<BeersResponse> call, Response<BeersResponse> response) {
                    //Get response body from json
                    BeerDB beer  = response.body().getData();
                    beerTitle.setText(beer.getName());
                    beerDescription.setText(beer.getDescription());
                    try {
                        String str = beer.getLabels().getIcon();
                        if (!(str == null)) {
                            //Set label image
                            URL newurl = new URL(beer.getLabels().getIcon());
                            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                            beerImageView.setImageBitmap(mIcon_val);
                        } else {
                            //Show error
                            Toast.makeText(getApplicationContext(), "Image label not available for " + beer.getName() + " beer", Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception e)
                    {
                        //Show error
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

    //Check for wifi or mobile data is on
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
