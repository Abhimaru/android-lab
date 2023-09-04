package com.example.readfile_db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "StudentDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_STUDENT_ID = "s_id";
    private static final String KEY_STUDENT_NAME = "s_name";
    private static final String KEY_STUDENT_EMAIL = "s_email";
    private static final String KEY_STUDENT_PHONE = "s_phone";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "(" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        KEY_STUDENT_ID + " TEXT UNIQUE NOT NULL, " +
                        KEY_STUDENT_NAME + " TEXT NOT NULL, " +
                        KEY_STUDENT_EMAIL + " TEXT NOT NULL, " +
                        KEY_STUDENT_PHONE + " TEXT NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    
    public void addStudentDetail(String id, String name, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, id);
        values.put(KEY_STUDENT_NAME, name);
        values.put(KEY_STUDENT_EMAIL, email);
        values.put(KEY_STUDENT_PHONE, phone);
        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<StudentModel> fetchStudents(){
        ArrayList<StudentModel> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()){
            StudentModel student = new StudentModel();
            student.id = cursor.getInt(0);
            student.s_id = cursor.getString(1);
            student.name = cursor.getString(2);
            student.email = cursor.getString(3);
            student.phone = cursor.getString(4);
            studentList.add(student);
        }
        return studentList;
    }

    public StudentModel getStudent(String studentId){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_STUDENT_ID + " = ?", new String[]{studentId});
        if(cursor != null){
            cursor.moveToFirst();
        }
        StudentModel student = new StudentModel();
        assert cursor != null;
        student.id = cursor.getInt(0);
        student.s_id = cursor.getString(1);
        student.name = cursor.getString(2);
        student.email = cursor.getString(3);
        student.phone = cursor.getString(4);
        return student;
    }
}
