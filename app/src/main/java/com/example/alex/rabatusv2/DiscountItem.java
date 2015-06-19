package com.example.alex.rabatusv2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by Peter on 18-06-2015.
 */
public class DiscountItem extends Activity {
    String discountCode;
    boolean itemChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting the layout.
        setContentView(R.layout.discount_checkbox_item);

        // Set listener for the checkbox.
        CheckBox checkBox = (CheckBox) findViewById(R.id.discount_checkbox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()) {
                    itemChecked = true;
                }
            }
        });
    }

    private String discountGenerator() {

        // Random object used for generating a mock discount code.
        Random r = new Random();

        //Creating the discount code.
        return discountCode = "" + (char) (r.nextInt(26) + 'a') + r.nextInt(100) + r.nextInt(100) + (char) (r.nextInt(26) + 'a')
                + (char) (r.nextInt(26) + 'a') + r.nextInt(100) + (char) (r.nextInt(26) + 'a') + r.nextInt(100);


    }
}
