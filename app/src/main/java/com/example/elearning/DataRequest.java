package com.example.elearning;

import android.content.Intent;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRequest {



    private List<Course> courseList;
    public List<Course> getCourseList() {
        return courseList;
    }


    private static class DataRequestHolder {
        private static final DataRequest INSTANCE = new DataRequest();
    }
    private String host = "http://192.168.137.1:8080/elearn/";
    public static final DataRequest getInstance(){
        return DataRequestHolder.INSTANCE;
    }

    private Retrofit retrofit;
    private DataRequest(){
        //使用retrofit封装的方法来获取course的json数据
        retrofit = new  Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //TODO 配置拦截器
    }

    public <T> T builder(Class<T> service) {
        if (null == service) {
            throw new RuntimeException("service can not be null!");
        }
        T t = this.retrofit.create(service);
        return t;
    }

    /**
     * 具体使用代码==各个业务场景自行调用
     */
    public void setCourseList(List<Course> netList){
        courseList = netList;
    }

    public void getCourseFormNet() {
        //创建网络请求接口的实例
        GetRequest_interface request = DataRequest.getInstance().builder(GetRequest_interface.class);
        //对发送请求进行数据封装
        Call<List<Course>> call = request.getCall();
        List<Course> coursesFromSpring = null;
        try {
            coursesFromSpring = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setCourseList(coursesFromSpring);
        //发送网络请求
       /* call.enqueue(new Callback<List<Course>>() {
            //请求成功时回调此函数
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                System.out.println("连接成功");
                //TODO setCourseList
                setCourseList(response.body());
            }
            //请求失败时回调此函数
            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                System.out.println("连接失败");
                t.printStackTrace();
            }
        });*/
    }

    public void test() {
         getCourseFormNet();
    }


}
