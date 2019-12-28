package com.example.elearning;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import  java.util.List;

public class courseinfor extends Activity{
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courseinfor);
        //顶部视频播放介绍
        VideoView videoView = (VideoView)findViewById(R.id.video);
        videoView.setVideoURI(Uri.parse("https://www.bilibili.com/video/av53534542?spm_id_from=333.851.b_62696c695f7265706f72745f6d75736963.9"));
        //videoView.start();

        mRecyclerView= (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,false));
        mAdapter=new MyAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        //初始化数据
        initData();


    }

    //初始化数据函数
    private void initData() {
        List<DataModle> dList=new ArrayList<>();
        for (int i=1;i<=3;i++){
            int type= i;//生成随机的Type
            DataModle data=new DataModle();
            data.setType(type);
            data.setName("name :"+i);
            dList.add(data);
        }

        mAdapter.addList(dList);
        mAdapter.notifyDataSetChanged();
    }

}