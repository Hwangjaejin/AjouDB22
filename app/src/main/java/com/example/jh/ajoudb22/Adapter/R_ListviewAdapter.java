package com.example.jh.ajoudb22.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.jh.ajoudb22.R;
import com.example.jh.ajoudb22.Item.RestaurantListitem;

import java.util.ArrayList;

public class R_ListviewAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<RestaurantListitem> items;
    private int layout;

    public R_ListviewAdapter(Context context, int layout, ArrayList<RestaurantListitem> items) {
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
        RestaurantListitem R_Listitem =items.get(i);

        ImageView imageView=(ImageView)view.findViewById(R.id.R_list_Image);
        TextView name=(TextView)view.findViewById(R.id.r_name);
        TextView rating=(TextView)view.findViewById(R.id.Rlist_rating_num);
        TextView review_num=(TextView)view.findViewById(R.id.Rlist_review_num);

        AQuery aq=new AQuery(view);

        aq.id(imageView).image(R_Listitem.getImage());
        name.setText(R_Listitem.getName());
        rating.setText(R_Listitem.getRating());
        review_num.setText(R_Listitem.getReview_num());

        return view;
    }
}
