package com.example.android_lab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;

    public ListAdapter(Activity context, String[] title) {
        super(context, R.layout.activity_list_adapter, title);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.title=title;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        @SuppressLint("ViewHolder") View rowView= inflater.inflate(R.layout.activity_list_adapter, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.iconImageView);

        titleText.setText(title[position]);
        imageView.setImageResource(R.drawable.code_icon);
        titleText.setText(title[position]);

        return rowView;
    };
}