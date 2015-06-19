package com.example.alex.rabatusv2;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;



/**
 * Created by Mathias Linde on 18-06-2015.
 */

public class OptionsActivity extends MainActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);


    }


    /*
    public void onClick(View v)
    {
        Drawable dr = getRequestedOrientation().
                getDrawable(R.drawable.button_pressed);
        dr.setColorFilter(Color.parseColor("#FF0000"),
                PorterDuff.Mode.SRC_ATOP);
        switch (v.getId())
        {
            case R.id.blue:
                if(button == null)
                {
                    button = (Button) findViewById(v.getId());
                } else {
                    button.setBackgroundResource(R.drawable.button_pressed);
                    button = (Button) findViewById(v.getId());
                }
                button.setBackgroundDrawable(dr);

                break;
        }
    }
    */

}