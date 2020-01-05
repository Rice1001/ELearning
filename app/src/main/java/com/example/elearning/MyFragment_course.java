package com.example.elearning;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
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

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

public class MyFragment_course extends Fragment {

    private List<Course> courseList;
    private RecyclerView recyclerView;
    private Course_adapter adapter;
    private  DataRequest dataRequest = DataRequest.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.full_course,container,false);
        //sendRequest();
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        new MyAsyncTask(recyclerView).execute();
        return view;

    }

  class MyAsyncTask extends AsyncTask<Void,Void,List<Course>>{

        private  RecyclerView rvView;
        public MyAsyncTask(RecyclerView recyclerView){
            rvView = recyclerView;
        }

      @Override
      protected List<Course> doInBackground(Void... voids) {
          dataRequest = DataRequest.getInstance();
          dataRequest.test();
          return dataRequest.getCourseList();
      }

      @Override
      protected void onPostExecute(final List<Course> courseList) {
          super.onPostExecute(courseList);
          LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity (),LinearLayoutManager.VERTICAL,false);
          rvView.setLayoutManager(layoutManager);
          adapter = new Course_adapter(courseList);
          adapter.setOnItemClickLitener(new Course_adapter.OnItemClickLitener() {
              @Override
              public void onItemClick(View view, int position) {
                  Intent intent = new Intent(getActivity(), CourseInfor.class);
                  intent.putExtra("courselist",(Serializable)(courseList.get(position)));
                  startActivity(intent);
              }
          });
          rvView.setAdapter(adapter);
      }
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
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getActivity(), CourseInfor.class);
                            intent.putExtra("courselist",(Serializable)(courseList.get(position)));
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                    break;
            }
        }
    };


}
