package com.example.elearning;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class TypeAbstractViewHolder extends RecyclerView.ViewHolder {

    public TypeAbstractViewHolder(View item_view){
        super(item_view);
    }

    public abstract void bindHolder(DataModle modle);

}
