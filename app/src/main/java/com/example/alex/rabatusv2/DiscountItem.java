package com.example.alex.rabatusv2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by Peter on 18-06-2015.
 */
public class DiscountItem extends Fragment {
    String discountCode;
    boolean itemChecked = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.discount_checkbox_item, container, false);
    }

    public static void packageIntent(Intent intent) {
        intent.putExtra("store name", "HejmeddigStore");

    }

    private String discountGenerator() {

        // Random object used for generating a mock discount code.
        Random r = new Random();

        //Creating the discount code.
        return discountCode = "" + (char) (r.nextInt(26) + 'a') + r.nextInt(100) + r.nextInt(100) + (char) (r.nextInt(26) + 'a')
                + (char) (r.nextInt(26) + 'a') + r.nextInt(100) + (char) (r.nextInt(26) + 'a') + r.nextInt(100);


    }
}
