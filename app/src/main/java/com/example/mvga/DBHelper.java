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
        DB.execSQL("create Table Userdetails(username TEXT primary key,password TEXT,email TEXT,name TEXT,gym_package TEXT,card TEXT)");
        DB.execSQL("create Table Trainer_details(username TEXT primary key,trainer_NameAndServices TEXT, curdate TEXT, month TEXT , year TEXT , time TEXT , duration TEXT )");
       // DB.execSQL("create Table Trainerdetails(trainername TEXT primary key, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");
    }
    public Boolean insertuserdata(String username, String password, String email, String name, String gym_package, String card)
    {
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("email",email);
        contentValues.put("name",name);
        contentValues.put("gym_package",gym_package);
        contentValues.put("card",card);
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

    public Boolean checkU_schedule(String username){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select * from Trainer_details where username= ?", new String[]{username});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkSchedule(String day, String month, String year, String time, String trainer_NameAndServices){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cday=DB.rawQuery("select * from Trainer_details where curdate= ?", new String[]{day});
        Cursor cmonth=DB.rawQuery("select * from Trainer_details where month= ?", new String[]{month});
        Cursor cyear=DB.rawQuery("select * from Trainer_details where year= ?", new String[]{year});
        Cursor ctime=DB.rawQuery("select * from Trainer_details where time= ?", new String[]{time});
        Cursor ct=DB.rawQuery("select * from Trainer_details where trainer_NameAndServices= ?", new String[]{trainer_NameAndServices});

        if(cday.getCount()>0 && cmonth.getCount()>0 && cyear.getCount() > 0 && ctime.getCount()>0 && ct.getCount()>0){
            return true;
        }
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
    public Cursor getSchedule(String username){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor res=DB.rawQuery("select * from Trainer_details where username= ?", new String[]{username});
        return res;
    }

    public Boolean CancelSchedule(String username){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select * from Trainer_details where username= ?", new String[]{username});
        if(cursor.getCount()>0){
            long result= DB.delete("Trainer_details", "username=?", new String[]{username});
            if(result==-1){
                return false;
            }
            else {
                return true;
            }
        }
        else{
            return  false;
        }

    }





}
