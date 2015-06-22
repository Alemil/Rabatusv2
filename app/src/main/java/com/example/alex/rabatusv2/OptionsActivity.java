package com.example.alex.rabatusv2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;



/**
 * Created by Mathias Linde on 18-06-2015.
 */

public class OptionsActivity extends MainActivity {

    private static int BACKGROUNDCOLOR = 0xff000000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup1);
        final SharedPreferences sp = getSharedPreferences("setting", MODE_PRIVATE);

        if(sp.getInt("checked", 0) != 0)
        {
            rg.check(sp.getInt("checked",0));
        }

        //final RelativeLayout bg = (RelativeLayout) findViewById(R.id.VIEW_PARENT);
        final LinearLayout bg = (LinearLayout) findViewById(R.id.linearlayout);
        bg.setBackgroundColor(BACKGROUNDCOLOR);
        final RadioButton rb_black = (RadioButton) findViewById(R.id.black);
        final RadioButton rb_blue = (RadioButton) findViewById(R.id.blue);
        final RadioButton rb_green = (RadioButton) findViewById(R.id.green);

        // Changes the background color to black when the radiobutton is pressed.
        // This one is by default pressed.
        rb_black.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(0xff000000);
                BACKGROUNDCOLOR = 0xff000000;
                sp.edit().putInt("bgcolor", BACKGROUNDCOLOR).apply();
            }
        });

        // Changes the background color to blue when the radiobutton is pressed.
        rb_blue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(0xff0baeff);
                BACKGROUNDCOLOR = 0xff0baeff;
                sp.edit().putInt("bgcolor", BACKGROUNDCOLOR).apply();
            }
        });

        // Changes the background color to green when the radiobutton is pressed.
        rb_green.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(0xff20b2aa);
                BACKGROUNDCOLOR = 0xff20b2aa;
                sp.edit().putInt("bgcolor", BACKGROUNDCOLOR).apply();
            }
        });

    }

    protected void onPause()
    {
        super.onPause();

        RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup1);
        int id = rg.getCheckedRadioButtonId();
        SharedPreferences sp = getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putInt("checked", id);
        e.commit();
    }

}