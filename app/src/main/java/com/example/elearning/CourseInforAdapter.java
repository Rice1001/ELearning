package com.example.elearning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseInforAdapter extends RecyclerView.Adapter<CourseInforAdapter.ViewHolder> {
    private Course mCourseList;


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView courseName,courseTime,courseDescription;

        public  ViewHolder(View view){
            super(view);
            courseName = (TextView)view.findViewById(R.id.course_name);
            courseTime = (TextView)view.findViewById(R.id.course_infor_rv_time);
            courseDescription = (TextView)view.findViewById(R.id.course_infor_rv_description);

        }
    }

    public CourseInforAdapter(Course courseList){
        this.mCourseList = courseList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.course_infor_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Course course = mCourseList;
        holder.courseName.setText(course.getName());
        holder.courseTime.setText(course.getOpenDate()+"到" + course.getLastUpdateOn());
        holder.courseDescription.setText(course.getDescription());
    }

    @Override
    public int getItemCount() {

        return 1;
    }


}
