package com.example.mvga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME= "mvga.db";
    public DBHelper(Context context) {

        super(context, "mvga.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(username TEXT primary key,password TEXT,email TEXT,name TEXT,gym_package TEXT)");
        DB.execSQL("create Table Trainer_details(username TEXT primary key,trainer_NameAndServices TEXT, curdate TEXT, month TEXT , year TEXT , time TEXT , duration TEXT )");
       // DB.execSQL("create Table Trainerdetails(trainername TEXT primary key, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public Boolean insertuserdata(String username, String password, String email, String name, String gym_package)
    {
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("email",email);
        contentValues.put("name",name);
        contentValues.put("gym_package",gym_package);
        long result=DB.insert("Userdetails", null, contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean insert_trainer_schedule(String username, String trainer_NameAndServices, String curdate, String month, String year, String time, String duration)
    {
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("trainer_NameAndServices",trainer_NameAndServices);
        contentValues.put("curdate",curdate);
        contentValues.put("month",month);
        contentValues.put("year",year);
        contentValues.put("time",time);
        contentValues.put("duration",duration);

        long result=DB.insert("Trainer_details", null, contentValues);

        if(result==-1)
            return false;
        else
            return true;
    }



    public Boolean checkusername(String username){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select * from Userdetails where username= ?", new String[]{username});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select * from Userdetails where username= ? and password= ?", new String[]{username,password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Cursor getAllData(String username){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor res=DB.rawQuery("select * from Userdetails where username= ?", new String[]{username});
        return res;
    }


}
