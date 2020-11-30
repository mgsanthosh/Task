package com.example.task;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "hello.db";
    public static final String TABLE_NAME = "table_hello";
    public static final String COL_1 = "roll_number";
    public static final String COL_2 = "student_name";
    public static final String COL_3 = "student_dob";
    public static final String COL_4 = "standard";
    public static final String COL_5 = "address";
    public static final String COL_6 = "Phone_number";
    public static final String COL_7 = "attendance";
    public static final String COL_8 = "Student_id";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(roll_number INTEGER PRIMARY KEY AUTOINCREMENT ,Student_id String, student_name TEXT , student_dob TEXT , standard TEXT ,address TEXT ,phone_number TEXT,attendance TEXT)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Student_id ,String student_name, String student_dob, String standard, String address, String phone_number, String attendance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, student_name);
        contentValues.put(COL_3, student_dob);
        contentValues.put(COL_4, standard);
        contentValues.put(COL_5, address);
        contentValues.put(COL_6, phone_number);
        contentValues.put(COL_7, attendance);
        contentValues.put(COL_8,Student_id);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public Cursor getPhoneNumber(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where Student_id= "+id+"", null);
        return res;

    }

    public Cursor getPhoneNumber1(String stuid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where Student_id= "+stuid+"", null);
        return res;

    }



}