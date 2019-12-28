package com.example.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class RegisterActivity extends AppCompatActivity {
    private Button sureButton;
    private Button backButton;
    private EditText registerIdEdit,registerPassEdit1,registerPassEdit2,registerMailEdit;
    private ElearningDate DBdate;

    @Override
    protected void onCreate(Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        sureButton = (Button)findViewById(R.id.sureButton);
        backButton = (Button)findViewById(R.id.backButton);
        //        获取注册时文本框对象
         registerIdEdit = (EditText)findViewById(R.id.registerUser);
         registerPassEdit1 = (EditText)findViewById(R.id.registerPass1);
         registerPassEdit2 = (EditText)findViewById(R.id.registerPassAgain);
         registerMailEdit = (EditText)findViewById(R.id.registerMail);
         DBdate = new ElearningDate(this);

         //检测用户注册时输入是否规范
        sureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取注册时用户输入
                String id = registerIdEdit.getText().toString(),
                        pass1 = registerPassEdit1.getText().toString(),
                        passAgain = registerPassEdit2.getText().toString(),
                        mail = registerMailEdit.getText().toString();
                Log.d("id",id);
                Log.d("pass1",pass1);
                Log.d("mail",mail);
                person user_register = new person(id,pass1,mail);
                if(!"".equals(id) && !"".equals(pass1) && !"".equals(passAgain) && !"".equals(mail)){
                    if(pass1.equals(passAgain))
                       checkIfExist(user_register);
                    else{
                        Toast.makeText(RegisterActivity.this,"两次密码输入不一致", Toast.LENGTH_LONG).show();
                        registerPassEdit2.setText("");
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this,"请将注册信息填写完整", Toast.LENGTH_LONG).show();
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_main = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent_main);
            }
        });
    }


    public void checkIfExist(person registerUser)
    {
        ArrayList<person> userList = (ArrayList<person>)DBdate.getAllUser();
        Iterator<person>  it = userList.iterator();
        while(it.hasNext())
        {
            if((it.next().getName()).equals(registerUser.getName()))
            {
                Toast.makeText(RegisterActivity.this,"用户名已经存在!请重新输入",Toast.LENGTH_SHORT).show();
            }
            else
            {
                DBdate.addUser(registerUser);
                Toast.makeText(RegisterActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);

            }
        }
    }

}
