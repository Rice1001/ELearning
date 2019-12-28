package com.example.elearning;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ElearningDate {
    private DBOpenHelper helper;
    private SQLiteDatabase db;

    public ElearningDate(Context context)
    {
        helper = new DBOpenHelper(context);
    }


    /**
     *
     *
     * @param person 注册用户对象
     *
     */
    public void addUser(person person)
    {
        db = helper.getWritableDatabase();
        db.execSQL("insert into user (id,password,mail) values(?,?,?)",new Object[]{person.getName(),
                    person.getPassword(),person.getMail()});
    }


    /**
     *
     * @return persons 用户集合
     */
    public List<person> getAllUser()
    {
        db = helper.getReadableDatabase();
        ArrayList<person> persons = new ArrayList<person>();
        Cursor c = db.rawQuery("select*from user",null);
        while(c.moveToNext())
        {
            person person  = new person();
            person.setName(c.getString(c.getColumnIndex("id")));
            person.setPassword(c.getString(c.getColumnIndex("password")));
            person.setMail(c.getString(c.getColumnIndex("mail")));
            persons.add(person);
        }
        return persons;
    }


    /**
     *
     * close the database
     */
    public void closeDB()
    {
        db.close();
    }


}
