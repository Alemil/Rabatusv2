package com.example.alex.rabatusv2;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Peter Fischer on 18-06-2015.
 */
public class DiscountListActivity extends ListActivity {
    DiscountAdapter mAdapter;
    private static final String DISCOUNT_PREF_KEY = "discount";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set layout & start the adapter.
        setContentView(R.layout.discount_list);
        mAdapter = new DiscountAdapter(getApplicationContext());


        // Get the saved DiscountItems
         Gson gson = new Gson();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Restore the saved data using the DISCOUNT_PREF_KEY.
        String restoredData = sharedPreferences.getString(DISCOUNT_PREF_KEY, null);

        if (restoredData != null) {

            // To get the data back into a list we need to know the type.
            Type listType = new TypeToken<ArrayList<DiscountItem>>() {

            }.getType();

            // Getting the data back into a list.
            List<DiscountItem> items = gson.fromJson(restoredData, listType);

            // The list is set to the list in the adapter.
            mAdapter.addAll(items);
        }

        //testing purpose.
        LayoutInflater layoutInflater = getLayoutInflater();
        TextView headerView = (TextView) layoutInflater.inflate(R.layout.footer_view, null);
        headerView.setText("Add items");
        getListView().setHeaderDividersEnabled(true);
        getListView().addHeaderView(headerView);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiscountItem discountItem = new DiscountItem();
                mAdapter.addItems(discountItem);
            }
        });


        // Inflate the footerView.
        //  LayoutInflater layoutInflater = getLayoutInflater();
        TextView footerView = (TextView) layoutInflater.inflate(R.layout.footer_view, null);

        // Put a divider between the elements in the list and the footer.
        getListView().setFooterDividersEnabled(true);

        // Adding the footer to the list.
        getListView().addFooterView(footerView);

        // Attaching a listener to the footer.
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete selected items on the list.
                Log.v("discountList", "The delete button was pressed. A footerView that is");

                mAdapter.deleteItems();

                Toast toast = new Toast(getApplicationContext());

                if(toast != null) {
                    toast.cancel();
                }
                toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();



            }
        });

        setListAdapter(mAdapter);


    }


    @Override
    protected void onPause() {
        super.onPause();

        // Save the list of DiscountItems.
        Gson gson = new Gson();

        // Store the list of DiscountItems into a String.
        String savedDiscount = gson.toJson(mAdapter.getList());

        // Save the data with the help of SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // The DISCOUNT_PREF_KEY is used to identify the data.
        sharedPreferences.edit().putString(DISCOUNT_PREF_KEY, savedDiscount).apply();


    }


}
