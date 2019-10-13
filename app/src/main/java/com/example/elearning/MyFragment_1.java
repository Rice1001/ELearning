package com.example.elearning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyFragment_1 extends Fragment {
    private String content;

    public MyFragment_1(String content){
        this.content = content;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment,container,false);
        TextView textContent =(TextView)view.findViewById(R.id.textContent);
        textContent.setText(content);
        return view;
    }
}
