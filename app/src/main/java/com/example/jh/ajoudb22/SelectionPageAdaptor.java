package com.example.jh.ajoudb22;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class SelectionPageAdaptor extends FragmentPagerAdapter {

    private static final int PAGE_1 = 0;
    private static final int PAGE_2 = 1;
    private static final int PAGE_3 = 2;
    private static final int PAGE_4 = 3;
    private static final int PAGE_5 = 4;
    private static final int PAGE_6 = 5;

    public SelectionPageAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) { // 표시할 Fragment
        switch (position) {
            case PAGE_1:
                return Fragment_Hansik.newInstance(); // 빨
            case PAGE_2:
                return Fragment_Yangsik.newInstance(); // 초
            case PAGE_3:
                return Fragment_JungSik.newInstance(); // 파
            case PAGE_4:
                return Fragment_IlSik.newInstance(); // 파
            case PAGE_5:
                return Fragment_Chicken.newInstance(); // 파
            case PAGE_6:
                return Fragment_Hamburger.newInstance(); // 파

            default:
                return null;
        }
    }

    @Override
    public int getCount() { // Tab 의 갯수
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "탭 " + position; // 페이지(position)에 따른 tab 의 타이틀 지정
    }
}
