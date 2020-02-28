package com.example.bizfollow;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "client.db";
    public static final String TABLE_NAME = "client_table";
    public static final String Col_0 = "ID";
    public static final String Col_1 = "NAME";
    public static final String Col_2 = "ADDRESS";
    public static final String Col_3 = "VEHICLE";
    public static final String Col_4 = "FOLLOWFREQ";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT, ADDRESS TEXT, VEHICLE TEXT, FOLLOWFREQ TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String address, String vehicle,String freq){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, name);
        contentValues.put(Col_2,address);
        contentValues.put(Col_3,vehicle);
        contentValues.put(Col_4,freq);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id, String name, String address, String vehicle, String freq){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_0,id);
        contentValues.put(Col_1,name);
        contentValues.put(Col_2,address);
        contentValues.put(Col_3,vehicle);
        contentValues.put(Col_4,freq);
        db.update(TABLE_NAME,contentValues,"ID = ?",new String[]{id});
        return true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }
}
