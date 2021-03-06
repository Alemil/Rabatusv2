package com.example.alex.rabatusv2;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Peter on 18-06-2015.
 */
public class DiscountAdapter extends BaseAdapter{

    private ArrayList<DiscountItem> discountItems = new ArrayList<>();
    private Context mContext;

    public DiscountAdapter(Context context) {
        mContext = context;
    }

    public void addItems(DiscountItem item) {
        discountItems.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<DiscountItem> items) {
        discountItems.addAll(items);
    }

    public List<DiscountItem> getList() {
        return discountItems;
    }

    public void deleteItems() {

        for (Iterator<DiscountItem> iterator = discountItems.iterator(); iterator.hasNext(); ) {
            DiscountItem currentItem = iterator.next();
            if (currentItem.itemChecked) {
                iterator.remove();


            }
        }

        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return discountItems.size();
    }

    @Override
    public Object getItem(int position) {
        return discountItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Not correct.
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the current item.
        final DiscountItem discountItem = (DiscountItem) getItem(position);

        // Inflate the item to a view.
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout discountItemView = (RelativeLayout) inflater.inflate(R.layout.discount_checkbox_item, null);

        // Set the appropriate changes to the view.
        TextView storeName = (TextView) discountItemView.findViewById(R.id.discount_name);
        storeName.setText("Store name: " + discountItem.storeName);

        TextView discountCode = (TextView) discountItemView.findViewById(R.id.discount_code);
        discountCode.setText("Discount code: " + discountItem.discountCode);

        CheckBox checkBox = (CheckBox) discountItemView.findViewById(R.id.discount_checkbox);
        checkBox.setChecked(discountItem.itemChecked);

        //Set up a listener, to listen for checkbox changes.
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                discountItem.setItemChecked(isChecked);
            }
        });

        return discountItemView;
    }
}
