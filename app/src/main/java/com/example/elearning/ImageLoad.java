package com.example.elearning;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ImageLoad {
    private ImageView mImageView;

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            mImageView.setImageBitmap((Bitmap)msg.obj);
        }
    };

    public void showImage(ImageView imageView, final String url){
        mImageView = imageView;
        new Thread(){
            @Override
            public void run() {
                super.run();
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
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            is= new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            return bitmap;
        }catch (java.io.IOException e){
            e.printStackTrace();
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return  null;
    }
}
