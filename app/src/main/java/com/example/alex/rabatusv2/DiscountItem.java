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

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Peter on 18-06-2015.
 */
public class DiscountItem  {
    boolean itemChecked = false;

  public DiscountItem() {

  }

    public static void packageIntent(Intent intent) {
        intent.putExtra("store name", "HejmeddigStore");

    }

    public String discountGenerator() {

        // Random object used for generating a mock discount code.
        Random r = new Random();
        String discountCode;
        //Creating the discount code.
        return discountCode = "" + (char) (r.nextInt(26) + 'a') + r.nextInt(100) + r.nextInt(100) + (char) (r.nextInt(26) + 'a')
                + (char) (r.nextInt(26) + 'a') + r.nextInt(100) + (char) (r.nextInt(26) + 'a') + r.nextInt(100);


    }

    // A method to return a random store name.
    public String storeNameGenerator() {
        ArrayList<String> storeNames = new ArrayList<>();
        storeNames.add("Poppi's Pizza");
        storeNames.add("Bryggertorvets Pizza");
        storeNames.add("Best PIzza");
        storeNames.add("Konge Burger");
        storeNames.add("Pomodo Rozzo");
        storeNames.add("Azzip");
        storeNames.add("Regrub");
        storeNames.add("The Fractured but Whole Pizza");
        storeNames.add("How low can you go Pizza");
        storeNames.add("Dante's Pizza");
        Random r = new Random();
        int randomNumber = r.nextInt(9);

        return storeNames.get(randomNumber);

    }
}
