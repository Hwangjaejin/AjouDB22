package com.example.jh.ajoudb22;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fragment_Hamburger extends Fragment {

    public static Fragment_Hamburger newInstance() {
        return new Fragment_Hamburger();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_hamburger, container, false);
    }
}
