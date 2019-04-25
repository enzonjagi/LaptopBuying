package com.example.laptopBuying;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="registerusers";
    public static final String COL_1="ID";
    public static final String COL_2="firstname";
    public static final String COL_3="lastname";
    public static final String COL_4="username";
    public static final String COL_5="email";
    public static final String COL_6="password";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registerusers (ID INTEGER PRIMARY KEY AUTOINCREMENT, firstname TEXT, lastname TEXT, username TEXT, email TEXT, password TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public long addUser( String fname, String lname ,String username, String mail, String password){
        SQLiteDatabase dab = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", fname);
        contentValues.put("lastname", lname);
        contentValues.put("username", username);
        contentValues.put("email", mail);
        contentValues.put("password", password);
        long res = dab.insert("registerusers", null, contentValues);
        dab.close();
        return res;

    }

    public boolean checkUser(String username, String password){
        String[] columns = {COL_4};
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_5 + "=?" + " and " + COL_6;
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count>0)
            return true;
        else
            return false;
    }
}
