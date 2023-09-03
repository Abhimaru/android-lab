package com.example.db_demo;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "CourseDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "course";
    private static final String KEY_COURSE_ID = "id";
    private static final String KEY_COURSE_NAME = "name";
     private static final String KEY_COURSE_DURATION = "duration";
    private static final String KEY_COURSE_TRACKS = "tracks";
    private static final String KEY_COURSE_DESCRIPTION = "description";
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + KEY_COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_COURSE_NAME + " TEXT NOT NULL," + KEY_COURSE_DURATION + " TEXT NOT NULL," + KEY_COURSE_TRACKS + " TEXT," + KEY_COURSE_DESCRIPTION +" TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addCourse(String name, String duration, String tracks, String description, Context context){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_NAME, name);
        values.put(KEY_COURSE_DURATION, duration);
        values.put(KEY_COURSE_TRACKS, tracks);
        values.put(KEY_COURSE_DESCRIPTION, description);
        db.insert(TABLE_NAME, null, values);
        Toast.makeText(context, "Course Added", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<CourseModel> fetchCourses(){
        ArrayList<CourseModel> courseList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while(cursor.moveToNext()){
            CourseModel course = new CourseModel();
            course.id = (cursor.getInt(0));
            course.name = (cursor.getString(1));
            course.duration = (cursor.getString(2));
            course.tracks = (cursor.getString(3));
            course.description = (cursor.getString(4));
            courseList.add(course);
        }
        return courseList;
    }

    public CourseModel fetchCourseById(int id) {
        CourseModel course = new CourseModel();
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_COURSE_ID + " = " + id, null);
        if(cursor.moveToFirst()){
            course.id = (cursor.getInt(0));
            course.name = (cursor.getString(1));
            course.duration = (cursor.getString(2));
            course.tracks = (cursor.getString(3));
            course.description = (cursor.getString(4));
        }
        return course;
    }

    public void deleteCourseById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_COURSE_ID + " = " + id, null);
    }
}
