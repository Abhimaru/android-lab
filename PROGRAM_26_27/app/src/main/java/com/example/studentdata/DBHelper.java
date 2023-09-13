package com.example.studentdata;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "studentDB";
    private static final String TABLE_NAME = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "fname";
    private static final String KEY_LNAME = "lname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_PINCODE = "pincode";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_FNAME + " TEXT, " +
                KEY_LNAME + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_PHONE + " TEXT, " +
                KEY_GENDER + " TEXT, " +
                KEY_ADDRESS + " TEXT, " +
                KEY_CITY + " TEXT, " +
                KEY_PINCODE + " TEXT" + ")");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addDetail(String f_name, String l_name, String email, String phone, String gender, String address, String city, String pincode){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, f_name);
        values.put(KEY_LNAME, l_name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phone);
        values.put(KEY_GENDER, gender);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_CITY, city);
        values.put(KEY_PINCODE, pincode);
        db.insert(TABLE_NAME, null, values);
    }
    public ArrayList<StudentModal> fetchDetails(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<StudentModal> studentList = new ArrayList<>();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while(cursor.moveToNext()){
            StudentModal student = new StudentModal();
            student.id = cursor.getInt(0);
            student.fname = cursor.getString(1);
            student.lname = cursor.getString(2);
            student.email = cursor.getString(3);
            student.phone = cursor.getString(4);
            student.gender = cursor.getString(5);
            student.address = cursor.getString(6);
            student.city = cursor.getString(7);
            student.pincode = cursor.getString(8);
            studentList.add(student);
        }
        return studentList;
    }


    public void updateStudent(int studentId, String fName, String lName, String email, String phone, String gender, String address, String city, String pincode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_FNAME, fName);
        values.put(KEY_LNAME, lName);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE, phone);
        values.put(KEY_GENDER, gender);
        values.put(KEY_ADDRESS, address);
        values.put(KEY_CITY, city);
        values.put(KEY_PINCODE, pincode);

        String[] s_id = {String.valueOf(studentId)};
        db.update(TABLE_NAME, values, KEY_ID + " = ?", s_id);
        db.close();
    }


    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id);
    }

    public ArrayList<StudentModal> searchStudents(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<StudentModal> studentList = new ArrayList<>();

        String selection = KEY_FNAME + " LIKE ? OR " +
                KEY_LNAME + " LIKE ? OR " +
                KEY_EMAIL + " LIKE ? OR " +
                KEY_PHONE + " LIKE ?";

        String[] selectionArgs = {"%" + query + "%", "%" + query + "%", "%" + query + "%", "%" + query + "%"};

        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);

        while (cursor.moveToNext()) {
            StudentModal student = new StudentModal();
            student.id = cursor.getInt(0);
            student.fname = cursor.getString(1);
            student.lname = cursor.getString(2);
            student.email = cursor.getString(3);
            student.phone = cursor.getString(4);
            student.gender = cursor.getString(5);
            student.address = cursor.getString(6);
            student.city = cursor.getString(7);
            student.pincode = cursor.getString(8);
            studentList.add(student);
        }

        cursor.close();
        return studentList;
    }

}
