package com.example.jh.ajoudb22;


import android.app.ListFragment;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private SelectionPageAdaptor mSelectionPageAdaptor;
    private ViewPager mViewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mSelectionPageAdaptor = new SelectionPageAdaptor(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        Intent intent = getIntent();

        final String userID = intent.getExtras().getString("userID");
        final String tab_id =  intent.getExtras().getString("tab_id");

        tabLayout = (TabLayout) findViewById(R.id.typeTabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        TextView idText = (TextView) findViewById(R.id.idText);
        idText.setText(userID + "님");
    }

    private void setupViewPager (ViewPager viewPager){
        SelectionPageAdaptor adaptor = new SelectionPageAdaptor(getSupportFragmentManager());

        adaptor.addFragment(new Fragment_Hansik(), "한식");
        adaptor.addFragment(new Fragment_Yangsik(), "양식");
        adaptor.addFragment(new Fragment_JungSik(), "중식");
        adaptor.addFragment(new Fragment_IlSik(), "일식");
        adaptor.addFragment(new Fragment_Chicken(), "치킨");
        adaptor.addFragment(new Fragment_Hamburger(), "햄버거");

        viewPager.setAdapter(adaptor);
    }
}


