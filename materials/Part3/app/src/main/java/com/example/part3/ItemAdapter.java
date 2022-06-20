package com.example.part3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ItemAdapter extends BaseAdapter {

    String[] items, prices, desc;
    LayoutInflater mInflater;

    public ItemAdapter(Context context, String[] items, String[] desc, String[] prices) {
        this.items = items;
        this. prices = prices;
        this.desc = desc;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.listview_details, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView descTextView = (TextView) v.findViewById(R.id.desTextView);
        TextView priceTextView = (TextView) v.findViewById(R.id.priceTextView);

        String name = items[position];
        String desci = desc[position];
        String price = prices[position];

        nameTextView.setText(name);
        descTextView.setText(desci);
        priceTextView.setText(price);

        return v;
    }
}
