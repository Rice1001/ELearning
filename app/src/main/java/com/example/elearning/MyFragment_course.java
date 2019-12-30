package com.example.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class MyFragment_course extends Fragment {

    private List<Course> courseList;
    private RecyclerView recyclerView;
    private Course_adapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.full_course,container,false);
        sendRequest();
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        return view;

    }

    //向服务器发送请求获取json数据
    public  void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://192.168.137.1:8080/elearn/courses")
                            .build();
                    Response response = client.newCall(request).execute();
                    System.out.println("成功连接");
                    String responsData = response.body().string();
                    parseJSONwhithGSON(responsData);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }


    //解析json数据
    public void parseJSONwhithGSON(String responsData) {
        Gson gson = new Gson();
        courseList = gson.fromJson(responsData, new TypeToken<List<Course>>()
       {}.getType());
       for(Course course:courseList){
           System.out.println(course.getName());
           System.out.println(courseList.size());
       }
        Message msg=new Message();
        msg.what=1;
        handler.sendMessage(msg);

    }

    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity (),LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(layoutManager);
                    adapter = new Course_adapter(courseList);
                    adapter.setOnItemClickLitener(new Course_adapter.OnItemClickLitener() {
                        @Override
                        public void onItemClick(View view, int posion) {
                            Intent intent = new Intent(getActivity(), CourseInfor.class);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    break;
            }
        }
    };
}
