package com.example.alex.rabatusv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Implement the four buttons.
        final Button startButton = (Button) findViewById(R.id.start_button);
        final Button optionsButton = (Button) findViewById(R.id.options_button);
        final Button couponButton = (Button) findViewById(R.id.discount_button);
        final Button highscoreButton = (Button) findViewById(R.id.highscore_button);

        // make the image.
        ImageView mImage = (ImageView) findViewById(R.id.logo_image);
        mImage.setImageResource(R.drawable.logo_image);

        // Setting up the buttons' onClickListeners
        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, GameActivity.class));
                Log.v("mainMenu","GameActivity started!");
            }
        });

        optionsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OptionsActivity.class));
                Log.v("mainMenu","OptionsMenu started!");
            }
        });

        couponButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DiscountListActivity.class));
                Log.v("mainMenu","DiscountActivity started!");
            }
        });

        highscoreButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HighscoreActivity.class));
                Log.v("mainMenu","HighscoreMenu started!");
            }
        });
    }
}
