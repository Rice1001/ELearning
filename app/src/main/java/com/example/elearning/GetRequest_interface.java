package com.example.elearning;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRequest_interface {

    //在注解中传入网络请求的部分URl地址,使用getCall（）来请求网络数据
    @GET("courses")
    Call<List<Course>> getCall();
}
