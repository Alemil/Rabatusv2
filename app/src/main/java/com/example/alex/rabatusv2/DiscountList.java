package com.example.alex.rabatusv2;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Mathias Linde on 18-06-2015.
 * Developed by Peter Fischer
 */
public class DiscountList extends ListActivity {
    DiscountAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Inflate the footerView.
        LayoutInflater layoutInflater = getLayoutInflater();
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
                Toast.makeText(getApplicationContext(), "shiit", Toast.LENGTH_SHORT).show();
                for (DiscountItem discountItem : mAdapter.discountItems) {
                    if (discountItem.itemChecked) {


                    }
                }


            }
        });

        // Setting the list adapter.
        setListAdapter(mAdapter);

        // Testing.

    }
}
