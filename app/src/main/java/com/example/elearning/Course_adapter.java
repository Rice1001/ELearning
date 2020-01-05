package com.example.elearning;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Course_adapter extends RecyclerView.Adapter<Course_adapter.ViewHolder> {
    private List<Course> my_list;
    private OnItemClickLitener mOnItemClickLitener;


    //
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView courseImage;
        TextView course_name, course_start;
        LinearLayout itemLayout;

        public ViewHolder(View view){
            super(view);
            //获取每一个子项布局的容器
            courseImage = (ImageView)view.findViewById(R.id.course_image);
            course_name = (TextView)view.findViewById(R.id.course_name);
            course_start = (TextView)view.findViewById(R.id.course_start);
            //recyclerview的子条目布局
            itemLayout = (LinearLayout)view.findViewById(R.id.itemLayout);

        }
    }
    //设置回调接口
    public interface OnItemClickLitener{
        void onItemClick(View view, int posion);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    //初始化子项数据
    public Course_adapter(List<Course> list){
        my_list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int type){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_item,parent,false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        Course course = my_list.get(position);
       // holder.course_image.setImageResource(course.getAvatar());
        holder.course_name.setText(course.getName());
        holder.course_start.setText(course.getOpenDate()+"");
        new ImageLoad().showImage(holder.courseImage,"http://192.168.137.1:8080/elearn/courses/"+course.getId()+"/photo");

        //通过条目设置点击事件触发回调
        if(mOnItemClickLitener != null){
            holder.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v,position);
                }
            });

        }
    }

    @Override
    public int getItemCount(){
       return my_list.size();
    }
}
