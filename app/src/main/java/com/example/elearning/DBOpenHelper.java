package com.example.elearning;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final  String DBNAME = "elearning";

    public DBOpenHelper(Context context){
        super(context,DBNAME, null,VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase db){
        db.execSQL("Create table user(id varchar(10) primary key,password varchar(20), mail varchar(30))");
        db.execSQL("Create table course(cno varchar(8) primary key,cname varchar(20),teacher varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){

    }

}
