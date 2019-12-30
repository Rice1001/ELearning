package com.example.elearning;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CourseInfor extends AppCompatActivity {

    private LinearLayout planLayout;
    private List<String> plan;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_infor_item);
        planLayout = (LinearLayout)findViewById(R.id.course_plan);
        //根据课程大纲数量王线性布局中添加教学安排
        addPlanLayout();

    }

    //实现往线性布局中添加课程大纲
    public void addPlanLayout(){
        plan = new ArrayList<String>();
        plan.add("学习工具IDEA的使用");
        plan.add("了解android的发展与使用");
        plan.add("布局设计");

        for(String str:plan){
            TextView tv = new TextView(this);
            tv.setText(str);
            planLayout.addView(tv);
        }


    }
}
