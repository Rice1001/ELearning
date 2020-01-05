package com.example.elearning;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ImageLoad {
    private ImageView mImageView;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            System.out.println("我已经设置了图片");
            mImageView.setImageBitmap((Bitmap)msg.obj);
        }
    };

    public void showImage(ImageView imageView, final String url){
        mImageView = imageView;
        new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println(url);
                Bitmap bitmap = getBitmapFromUrl(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
        }.start();

    }

    //通过解析数据库获取的图片链接获取课程封面图
    public Bitmap getBitmapFromUrl(String urlString){
        Bitmap bitmap;
        InputStream is = null;
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        }catch (java.io.IOException e){
            e.printStackTrace();
        }finally{
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return  null;
    }
}
