package com.example.elearning;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
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
    //sharedprefence
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private TextView forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref  = PreferenceManager.getDefaultSharedPreferences(this);
        rememberPass = (CheckBox)findViewById(R.id.remember_pass);
        boolean isRemember = pref.getBoolean("remember_password",false);

        //获取用户名及密码输入框对象
        userText = (EditText)findViewById(R.id.userEdit);
        passText = (EditText)findViewById(R.id.passwordEdit);
        loginButton = (Button)findViewById(R.id.loginButton);

        //获取忘记密码选项
        forget = (TextView)findViewById(R.id.forgetPassword);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgetPass.class);
                startActivity(intent);
            }
        });

        //检测是否勾选记住密码框
        if(isRemember){
            String account = pref.getString("account","");
            String pass = pref.getString("password","");
            userText.setText(account);
            passText.setText(pass);
            rememberPass.setChecked(true);

        }

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
        ArrayList<Person> userList = (ArrayList<Person>) DBdate.getAllUser();
        Iterator<Person> it = userList.iterator();
        Person userSeek;
        while(it.hasNext())
        {
            userSeek = it.next();
            if((userSeek.getName()).equals(userId)&& (userSeek.getPassword()).equals(pass))
            {
                //将用户的输入存储到sharedprefence中去
                editor = pref.edit();
                if(rememberPass.isChecked()){
                    editor.putBoolean("remember_password",true);
                    editor.putString("account",userSeek.getName());
                    editor.putString("password",userSeek.getPassword());
                }else{
                    editor.clear();
                }
                editor.apply();

                Intent intent = new Intent(MainActivity.this, firstActivity.class);
                intent.putExtra("id",userId);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(MainActivity.this,"重新输入",Toast.LENGTH_SHORT).show();
                passText.setText("");
            }

        }

    }


    //注册跳转
    public void register(){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }



}
