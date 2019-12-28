package com.example.elearning;

import android.view.View;
import android.widget.TextView;

public class TypeOneViewHolder extends TypeAbstractViewHolder {

    private TextView attend;

    public TypeOneViewHolder(View item_view){
        super(item_view);
        attend = (TextView)item_view.findViewById(R.id.rv_attendence);
    }

    @Override
    public void bindHolder(DataModle modle)
    {

        attend.setText(modle.getName());
    }
}
