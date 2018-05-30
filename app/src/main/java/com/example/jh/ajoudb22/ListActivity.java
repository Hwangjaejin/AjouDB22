package com.example.jh.ajoudb22;


import android.app.ListFragment;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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


        tabLayout=(TabLayout)findViewById(R.id.typeTabLayout);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        mViewPager=(ViewPager)findViewById(R.id.viewPager);
        mSelectionPageAdaptor=new SelectionPageAdaptor(getSupportFragmentManager());
        mViewPager.setAdapter(mSelectionPageAdaptor);

        tabLayout.setupWithViewPager(mViewPager);

        TextView idText = (TextView) findViewById(R.id.idText);
        idText.setText(userID + "님");
    }
}


