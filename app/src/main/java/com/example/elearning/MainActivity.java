package com.example.elearning;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity{
    private Button loginButton;
    private TextView registerView;
    private Thread thread;
    private ResultSet resultset;
    private EditText userText;
    private EditText passText;
    private String userId,pass;
    private ElearningDate DBdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取用户名及密码输入
        userText = (EditText)findViewById(R.id.userEdit);
        passText = (EditText)findViewById(R.id.passwordEdit);
        loginButton = (Button)findViewById(R.id.loginButton);
        DBdate = new ElearningDate(this);

//        检查用户输入是否为空
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = userText.getText().toString();
                pass = passText.getText().toString();
                if(!"".equals(userId)&&!"".equals(pass)){
                    checkLogin();
                }else{
                    Toast.makeText(MainActivity.this,"请将信息填写完整",Toast.LENGTH_LONG).show();
                }

            }
        });
        registerView = (TextView)findViewById(R.id.register);
        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }


    //登录跳转
    public void checkLogin()
    {
        ArrayList<person> userList = (ArrayList<person>) DBdate.getAllUser();
        Iterator<person> it = userList.iterator();
        person  userSeek;
        while(it.hasNext())
        {
            userSeek = it.next();
            if((userSeek.getName()).equals(userId)&& (userSeek.getPassword()).equals(pass))
            {
                Intent intent = new Intent(MainActivity.this,firstActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                passText.setText("");
            }

        }

    }


    //注册跳转
    public void register(){
        Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }



}
