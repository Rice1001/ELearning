package com.example.elearning;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TypeTwoViewHolder extends TypeAbstractViewHolder {

    private TextView str;
    public TypeTwoViewHolder(View item_view){
        super(item_view);
        str = (TextView)item_view.findViewById(R.id.rv3_tree);
    }

    @Override
    public void bindHolder(DataModle modle)
    {

        str.setText(modle.getName());
    }
}
