package com.example.db_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCourseAdapter extends BaseAdapter {
    Context context;
    ArrayList<CourseModel> courseList;

    public MyCourseAdapter(Context context, ArrayList<CourseModel> courseList) {
        this.context = context;
        this.courseList = courseList;
    }
    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return courseList.get(position).id;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.course, null);

        TextView cid = convertView.findViewById(R.id.c_id_p22_23);
        TextView cname = convertView.findViewById(R.id.c_name_p22_23);
        TextView c_duration = convertView.findViewById(R.id.c_duration_p22_23);

        cid.setText(String.valueOf(courseList.get(position).id));
        cname.setText(courseList.get(position).name);
        c_duration.setText(String.format("Duration: %s", courseList.get(position).duration));
        return convertView;
    }
}