package com.example.elearning;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;

public class MyFragment_1 extends Fragment {
    private String content;

    public MyFragment_1(String content){
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment,container,false);
        TextView courseName = (TextView)view.findViewById(R.id.course_name);
        courseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),courseinfor.class);
                startActivity(intent);
            }
        });
        return view;

    }

    //播放按钮事件
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


    }
}
