package com.example.customelistview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] title;

    public MyListAdapter(Activity context, String[] title) {
        super(context, R.layout.mylist, title);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.title=title;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        @SuppressLint("ViewHolder") View rowView= inflater.inflate(R.layout.mylist, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.iconImageView);

        titleText.setText(title[position]);
        imageView.setImageResource(R.drawable.foler_icon);
        titleText.setText(title[position]);

        return rowView;
    };
}
