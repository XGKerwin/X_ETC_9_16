package com.example.x_etc_9_16.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class X_Spinner_adapter extends BaseAdapter {
    private String[] arr;

    public X_Spinner_adapter(String[] arr) {
        this.arr =arr;
    }

    @Override
    public int getCount() {
        return arr.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view  =View.inflate(parent.getContext(),android.R.layout.simple_list_item_1,null);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(arr[position]);
        textView.setTextSize(30);
        textView.setTextColor(Color.BLACK);

        return view;
    }
}
