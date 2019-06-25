package com.example.asus.seekbardiy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
private ArrayList<String> mList=new ArrayList<>();
private AdViewSwitcher adViewSwitcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        adViewSwitcher=findViewById(R.id.adViewSwitcher);
        adViewSwitcher.addView(R.layout.activity_viewswitcher_item);
        mList.add("第1条广告");
        mList.add("第2条广告");
        mList.add("第3条广告");
        mList.add("第4条广告");
        mList.add("第5条广告");
        mList.add("第6条广告");
        mList.add("第7条广告");
        mList.add("第8条广告");
        mList.add("第9条广告");
        mList.add("第10条广告");
        mList.add("第11条广告");



        adViewSwitcher.upDataListAndView(mList, 3000);
        adViewSwitcher.setOnClickListener(new AdViewSwitcher.OnClickItemListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(Main4Activity.this,
                        "你点击了第"+position+"条广告",Toast.LENGTH_LONG).show();
            }
        });
        adViewSwitcher.startLooping();
    }

}