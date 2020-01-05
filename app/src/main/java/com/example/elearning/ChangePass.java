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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ChangePass extends AppCompatActivity {
    private DBOpenHelper dbOpenHelper;
    private EditText originEdit,newEdit;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.change_pass);
        //获取修改密码的用户id以及其原始登录密码
        final String userId = getIntent().getStringExtra("id");
        final String userPass = getIntent().getStringExtra("pass");

        //获取UI操作对象
        Button sure = (Button)findViewById(R.id.sure_bt);
        Button back =(Button)findViewById(R.id.back_bt);
        originEdit = (EditText)findViewById(R.id.origin_pass);
        newEdit = (EditText)findViewById(R.id.new_pass);



        //获取sqlite操作对象
        dbOpenHelper = new DBOpenHelper(this);

        //设置返回按钮事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePass.this,firstActivity.class);
                startActivity(intent);
            }
        });

        //设置确定按钮事件
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户的输入
                String origin = originEdit.getText().toString();
                String newPass = newEdit.getText().toString();

                if(origin.equals(userPass)){
                    changePassword(userId, newPass);
                }else{
                    Toast.makeText(ChangePass.this,"原密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    //修改密码
    public void changePassword(String idIn, String passIn){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", passIn);
        db.update("user",values,"id=?",new String[]{idIn});
        Toast.makeText(ChangePass.this,"密码修改成功",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ChangePass.this,firstActivity.class);
        startActivity(intent);
    }
}
