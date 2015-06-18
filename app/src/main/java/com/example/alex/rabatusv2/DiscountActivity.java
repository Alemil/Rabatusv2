package com.example.alex.rabatusv2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by Mathias Linde on 18-06-2015.
 */
public class DiscountActivity extends Activity {

    //The main view.
    private LinearLayout mFrame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.discount_activity);

        //Set the layout.
        mFrame = (LinearLayout) findViewById(R.id.Discount_Main_Layout);


    }
}
