package com.example.elearning;

import android.view.View;
import android.widget.TextView;

public class TypeThreeViewHolder extends TypeAbstractViewHolder {

    private TextView high, medium,low ;
    public TypeThreeViewHolder(View item_view){
        super(item_view);
        high = (TextView)item_view.findViewById(R.id.rv2_high);
    }

    @Override
    public void bindHolder(DataModle modle)
    {
        high.setText(modle.getName());
    }
}
