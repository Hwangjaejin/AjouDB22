package com.example.jh.ajoudb22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<MenuListitem> items;
    private int layout;

    public ListviewAdapter(Context context, int layout, ArrayList<MenuListitem> items) {
        this.inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public String getItem(int position) {
        return items.get(position).getName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view=inflater.inflate(layout,viewGroup,false);
        }
        MenuListitem menuListitem =items.get(i);

        TextView name=(TextView)view.findViewById(R.id.menu_name);
        TextView price=(TextView)view.findViewById(R.id.menu_price);

        name.setText(menuListitem.getName());

        price.setText(String.format("%,d",Integer.parseInt(menuListitem.getPrice()))+"원");

        return view;
    }
}
