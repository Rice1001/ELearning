package com.example.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MyFragment_user extends Fragment {

    private String userId;
    private String userPass;
    private ElearningDate DBdate;
    private DBOpenHelper dbHelper;

    public MyFragment_user(){}

    public MyFragment_user(String idIn, String passIn){
        userId = idIn;
        userPass = passIn;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.user_center,container,false);
        TextView userName = (TextView)view.findViewById(R.id.user_name);
        userName.setText(userId);
        dbHelper = new DBOpenHelper(getContext());

        TextView changePass = (TextView)view.findViewById(R.id.changePass);
        //跳转到改变密码页面
        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ChangePass.class);
                intent.putExtra("id",userId);
                intent.putExtra("pass",userPass);
                startActivity(intent);
            }
        });

       TextView exit = (TextView)view.findViewById(R.id.exit);
       exit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(),MainActivity.class);
               startActivity(intent);
           }
       });
        return view;
    }

    //获取数据库中的所有用户对象
    public Person getAllUser(){
        ArrayList<Person> userList = (ArrayList<Person>) DBdate.getAllUser();
        Iterator<Person> it = userList.iterator();
        Person userSeek;
        return null;
    }
}
