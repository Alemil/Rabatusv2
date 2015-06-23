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

public class OptionsActivity extends Activity {

    // Sætter en BACKGROUNDCOLOR variabels default værdi til sort.
    private static int BACKGROUNDCOLOR = 0xff000000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup1);

        final SharedPreferences sp = getSharedPreferences("setting", MODE_PRIVATE);

        /*
            Den følgende kodestump gør at den valgte RadioButton forbliver trykket.
            Så når spilleren går væk fra Optionsmenuen, og derefter tilbage, vil
            den samme RadioButton stadig være afkrydset.
         */
        if(sp.getInt("check", 0) != 0)
        {
            rg.check(sp.getInt("check",0));
        }

        /*
            Herunder opsætter vi de forskellige RadioButtons og RadioGroup til
            de tilhørende xml id's.
         */
        final LinearLayout bg = (LinearLayout) findViewById(R.id.linearlayout);
        bg.setBackgroundColor(BACKGROUNDCOLOR);
        final RadioButton rb_copperred = (RadioButton) findViewById(R.id.copperred);
        final RadioButton rb_blue = (RadioButton) findViewById(R.id.blue);
        final RadioButton rb_green = (RadioButton) findViewById(R.id.green);

        // Changes the background color to copper-red when the radiobutton is pressed.
        // This one is by default pressed.
        rb_copperred.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(0xffcb6d51);
                BACKGROUNDCOLOR = 0xffcb6d51;
                sp.edit().putInt("bgcolor", BACKGROUNDCOLOR).apply();
            }
        });

        // Changes the background color to blue when the radiobutton is pressed
        rb_blue.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(0xff0baeff);
                BACKGROUNDCOLOR = 0xff0baeff;
                sp.edit().putInt("bgcolor", BACKGROUNDCOLOR).apply();
            }
        });

        // Changes the background color to green when the radiobutton is pressed
        rb_green.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bg.setBackgroundColor(0xff49b675);
                BACKGROUNDCOLOR = 0xff49b675;
                sp.edit().putInt("bgcolor", BACKGROUNDCOLOR).apply();
            }
        });

    }

    /*
       SharedPreference sikrer at værdierne forbliver de samme og kontrollerer dem
       inden opbevaringen. Det vil sige, mere praktisk talt, at når spilleren har valgt
       en baggrundsfarve, forbliver det denne farve når spilleren forlader Optionsmenuen
       og går ind i selve spillet.
     */

    protected void onPause()
    {
        super.onPause();

        RadioGroup rg = (RadioGroup) findViewById(R.id.radiogroup1);
        int id = rg.getCheckedRadioButtonId();
        SharedPreferences sp = getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();
        e.putInt("check", id);
        e.commit();
    }

}