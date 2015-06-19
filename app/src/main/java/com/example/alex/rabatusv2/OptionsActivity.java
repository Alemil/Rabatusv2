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
import android.widget.LinearLayout;
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

        final LinearLayout bg = (LinearLayout) findViewById(R.id.linearlayout);

        final RadioButton rb_white = (RadioButton) findViewById(R.id.white);
        final RadioButton rb_blue = (RadioButton) findViewById(R.id.blue);
        final RadioButton rb_green = (RadioButton) findViewById(R.id.green);

        rb_blue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(0xff0baeff);
            }
        });

        rb_green.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(0xff20b2aa);
            }
        });

        rb_white.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(0xffffffff);
            }
        });

    }
}