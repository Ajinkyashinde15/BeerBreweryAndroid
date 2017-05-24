package com.brewery.beer.beerbreweryandroid;
/**
 * Created by Ajinkya
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    Button nextButton;
    TextView beerTitle;
    ImageView beerImageView;
    MultiAutoCompleteTextView beerDescription;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nextButton=(Button)findViewById(R.id.nextButton);
        beerTitle=(TextView)findViewById(R.id.beerTitle);
        beerImageView=(ImageView)findViewById(R.id.beerImageView);
        beerDescription=(MultiAutoCompleteTextView)findViewById(R.id.beerDescription);


    }
}
