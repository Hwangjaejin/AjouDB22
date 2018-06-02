package com.example.jh.ajoudb22.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jh.ajoudb22.Fragment.Fragment_Chicken;
import com.example.jh.ajoudb22.Fragment.Fragment_Hamburger;
import com.example.jh.ajoudb22.Fragment.Fragment_Hansik;
import com.example.jh.ajoudb22.Fragment.Fragment_IlSik;
import com.example.jh.ajoudb22.Fragment.Fragment_JungSik;
import com.example.jh.ajoudb22.Fragment.Fragment_Yangsik;

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
        switch (position) {
            case PAGE_1:
                return "한식"; // 빨
            case PAGE_2:
                return "양식"; // 초
            case PAGE_3:
                return "중식"; // 파
            case PAGE_4:
                return "일식"; // 파
            case PAGE_5:
                return "치킨"; // 파
            case PAGE_6:
                return "햄버거"; // 파
        }
        return "";
    }
}
