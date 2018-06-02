package com.example.jh.ajoudb22;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jh.ajoudb22.Adapter.SelectionPageAdaptor;
import com.example.jh.ajoudb22.Fragment.Fragment_Hansik;

public class ListActivity extends AppCompatActivity {

    private SelectionPageAdaptor mSelectionPageAdaptor;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();

        final String userID = intent.getExtras().getString("userID");
        final String tab_id =  intent.getExtras().getString("tab_id");

        UserID_Singleton singleton=UserID_Singleton.getInstance();
        singleton.setUserID(userID);

        tabLayout=(TabLayout)findViewById(R.id.typeTabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#000000"));

        mViewPager=(ViewPager)findViewById(R.id.viewPager);
        mSelectionPageAdaptor=new SelectionPageAdaptor(getSupportFragmentManager());
        mViewPager.setAdapter(mSelectionPageAdaptor);

        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(Integer.parseInt(tab_id));
    }
}


