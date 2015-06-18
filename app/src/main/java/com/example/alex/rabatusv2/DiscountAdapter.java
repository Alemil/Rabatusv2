package com.example.alex.rabatusv2;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Peter on 18-06-2015.
 */
public class DiscountAdapter extends BaseAdapter{

    ArrayList<DiscountItem> discountItems = new ArrayList<>();


    public void addItems(DiscountItem item) {
        discountItems.add(item);
    }

    public void clearItems() {
        discountItems.clear();
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
        return null;
    }
}
