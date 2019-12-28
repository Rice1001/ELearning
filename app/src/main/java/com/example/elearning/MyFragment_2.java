package com.example.elearning;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class MyFragment_2 extends Fragment {
    @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.search_layout,container,false);
//        String query = "select cname,cno,teacher from course as n,cno,t where ";
//        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(":memory:", null);
//        Cursor cursor = db.rawQuery(query, null);
//        String sqliteVersion = "";
//        if (cursor.moveToNext()) {
//            sqliteVersion = cursor.getString(0);
//        }
        return  view;
    }


    private void setListeners(){

    }
}
