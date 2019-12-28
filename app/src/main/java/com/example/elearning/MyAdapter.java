package com.example.elearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import  java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<DataModle> mList=new ArrayList<>();
    private Context mcontext;
    private  LayoutInflater my_layout_inflater;


    public MyAdapter(Context context) {
        this.mcontext = context;
        my_layout_inflater = LayoutInflater.from(context);
    }

    public void addList( List<DataModle> List){
        mList.addAll(List);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType){
            case 1:
                return new TypeOneViewHolder(my_layout_inflater.inflate(R.layout.type_course_infor_layout,parent,false));
            case 2:
                return new TypeTwoViewHolder(my_layout_inflater.inflate(R.layout.rv3_list_layout,parent,false));
            case 3:
                return new TypeThreeViewHolder(my_layout_inflater.inflate(R.layout.rv_recmdtion_layout,parent,false));
        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //抽象出TypeAbstartViewHolder，所以在进行绑定的时候可以直接调用
        ((TypeAbstractViewHolder)holder).bindHolder(mList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
