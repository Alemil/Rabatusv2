package com.example.alex.rabatusv2;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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

        setContentView(R.layout.discount_list);
        mAdapter = new DiscountAdapter(getApplicationContext());

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
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                for (DiscountItem discountItem : mAdapter.getList()) {
                    if (discountItem.itemChecked) {
                       mAdapter.getList().remove(discountItem);

                    }
                }


            }
        });

        setListAdapter(mAdapter);


    }

    // Only for testing, this is you start an activity for result.
    /*
    Intent discountData = new Intent();
                DiscountItem.packageIntent(discountData);
                setResult(RESULT_OK, discountData);

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK) {

            DiscountItem discountItem = new DiscountItem();
            mAdapter.addItems(discountItem);
        }

    }*/
}
