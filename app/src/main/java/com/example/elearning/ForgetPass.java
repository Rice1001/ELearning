package com.example.elearning;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;

public class ForgetPass extends AppCompatActivity {
    private EditText mailEdit,newEdit,userEdit;

    private ElearningDate DBdate;
    private DBOpenHelper dbOpenHelper;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.forget_pass);
        //获取UI操作对象
        mailEdit = (EditText)findViewById(R.id.mail);
        newEdit = (EditText)findViewById(R.id.new_pass);
        userEdit = (EditText)findViewById(R.id.user_edit);
        Button back = (Button)findViewById(R.id.back_bt);
        Button sure = (Button)findViewById(R.id.sure_bt);

        //添加返回按钮事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPass.this,MainActivity.class);
                startActivity(intent);
            }
        });

        DBdate = new ElearningDate(this);
        dbOpenHelper = new DBOpenHelper(this);
        //添加确定按钮事件
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = userEdit.getText().toString();
                String userMail = mailEdit.getText().toString();
                String pass = newEdit.getText().toString();
                if(checkMail(userID,userMail)){
                    changePass(userID, pass);
                }
            }
        });

    }

    //检查邮箱是否正确
    public boolean checkMail(String idIn, String mailIn){
        ArrayList<Person> userList = (ArrayList<Person>) DBdate.getAllUser();
        Iterator<Person> it = userList.iterator();
        Person userSeek;
        while (it.hasNext()){
            userSeek = it.next();
            if(userSeek.getName().equals(idIn)&&userSeek.getMail().equals(mailIn)){
                return true;
            }else{
                Toast.makeText(ForgetPass.this,"用户名或邮箱错误",Toast.LENGTH_LONG).show();
            }

        }
        Toast.makeText(ForgetPass.this,"无此用户",Toast.LENGTH_LONG).show();
        return false;
    }

    public void  changePass(String idIn, String passIn){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", passIn);
        db.update("user",values,"id=?",new String[]{idIn});
        Toast.makeText(ForgetPass.this,"密码修改成功",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ForgetPass.this,firstActivity.class);
        startActivity(intent);
    }
}
