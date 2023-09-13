package com.example.studentdata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<StudentModal> {
    private final Context context;
    private final ArrayList<StudentModal> students;

    public StudentAdapter(Context context, ArrayList<StudentModal> students) {
        super(context, R.layout.list_item, students);
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.list_item, parent, false);

        TextView nameTextView = rowView.findViewById(R.id.nameTextView_p26_27);
        TextView emailTextView = rowView.findViewById(R.id.emailTextView_p26_27);

        StudentModal student = students.get(position);

        nameTextView.setText(String.format("%s %s", student.fname, student.lname));
        emailTextView.setText(student.email);

        return rowView;
    }
}
