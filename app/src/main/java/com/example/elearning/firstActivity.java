package com.example.elearning;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class firstActivity   extends AppCompatActivity implements  RadioGroup.OnCheckedChangeListener{
    private RadioGroup myRadioGroup;
    private RadioButton myRadioButton_1;
    private MyFragment_course fg1;
    private MyFragment_search fg2;
    private MyFragment_user fg3;
    private FragmentManager fgManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        fgManager = getSupportFragmentManager();
        myRadioGroup = (RadioGroup)findViewById(R.id.radioGroupButton);
        myRadioGroup.setOnCheckedChangeListener(this);
        myRadioButton_1 = (RadioButton)findViewById(R.id.radioButtonHome);
        myRadioButton_1.setChecked(true);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId){
        FragmentTransaction fgTransaction = fgManager.beginTransaction();
        hideAllFragment(fgTransaction);
        switch(checkedId){
            case R.id.radioButtonHome:
                if(fg1 == null){
                    fg1= new MyFragment_course();
                    fgTransaction.add(R.id.home_container,fg1);
                }else{
                    fgTransaction.show(fg1);
                }
                break;
            case R.id.radioButtonSearch:
                if(fg2 == null){
                    fg2 = new MyFragment_search();
                    fgTransaction.add(R.id.home_container,fg2);
                }else{
                    fgTransaction.show(fg2);
                }
                break;
            case R.id.radioButtonUser:
                if(fg3 == null){
                    fg3 = new MyFragment_user();
                    fgTransaction.add(R.id.home_container,fg3);
                }else{
                    fgTransaction.show(fg3);
                }
                break;
        }
        fgTransaction.commit();

    }


    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
    }


}
