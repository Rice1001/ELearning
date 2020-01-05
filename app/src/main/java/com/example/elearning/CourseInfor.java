package com.example.elearning;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


public class CourseInfor extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private Course mCourseList;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_infor);
        //获取recyclerview布局对象
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //获取从课程界面传递过来的数据包
        mCourseList = (Course)getIntent().getSerializableExtra("courselist");
        CourseInforAdapter mAdapter = new CourseInforAdapter(mCourseList);
        recyclerView.setAdapter(mAdapter);

        //通过第三方库加载视频
        final JCVideoPlayerStandard videoplayer = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);
        boolean setUp = videoplayer.setUp("http://192.168.137.1:8080/elearn/materials/1/media", JCVideoPlayer.SCREEN_LAYOUT_LIST, "大眼瞪小眼");
        if (setUp) {
            videoplayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
             Glide.with(this).load("https://www.google.com/url?sa=i&source=images&cd=&ved=2ahUKEwi2zY_Fo-zmAhUjHjQIHbYnBIMQjRx6BAgBEAQ&url=https%3A%2F%2Fwww.canva.cn%2Flearn%2Femotional-pictures%2F&psig=AOvVaw1TH2BQQ3dzrrggKZ8IWP8c&ust=1578307402000366").into(videoplayer.thumbImageView);
        }
    }

    @Override
    public void onBackPressed(){
        if (JCVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
