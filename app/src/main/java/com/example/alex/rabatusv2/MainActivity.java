package com.example.alex.rabatusv2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
    // Temp:
    int count = 0;
    int counts = 0;
    int count_coupon = 0;
    int score_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button startButton = (Button) findViewById(R.id.start_button);
        final Button optionsButton = (Button) findViewById(R.id.options_button);
        final Button couponButton = (Button) findViewById(R.id.discount_button);
        final Button highscoreButton = (Button) findViewById(R.id.highscore_button);

        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v){
                startButton.setText("Got pressed: " + ++count);
            }
        });

        optionsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                optionsButton.setText("Got pressed: " + ++counts);
            }
        });

        couponButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                couponButton.setText("Got pressed: " + ++count_coupon);
            }
        });

        highscoreButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                highscoreButton.setText("Got pressed: " + ++score_count);
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
