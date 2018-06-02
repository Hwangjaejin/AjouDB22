package com.example.jh.ajoudb22.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jh.ajoudb22.R;
import com.example.jh.ajoudb22.Adapter.R_ListviewAdapter;
import com.example.jh.ajoudb22.RestaurantActivity;
import com.example.jh.ajoudb22.Item.RestaurantListitem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.floor;


public class Fragment_JungSik extends Fragment {

    private ArrayList<RestaurantListitem> Items;
    private ListView listView;
    private R_ListviewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_restaurant_list, null);
        listView=(ListView)view.findViewById(R.id.restaurant_listview);

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url="http://jaejindb.cafe24.com/RestaurantList.php";

        Items=new ArrayList<>();
        adapter=new R_ListviewAdapter(getActivity(),R.layout.item_restaurant,Items);

        final StringRequest strRequest2 = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONArray jsonArray=new JSONArray(response.toString());
                            Log.e("response",response.toString());
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonRow = jsonArray.getJSONObject(i);

                                String avg_rating=jsonRow.getString("avg");
                                double avg_rating_number=Double.parseDouble(avg_rating);
                                avg_rating_number=(floor(10*avg_rating_number))/10;

                                RestaurantListitem item=new RestaurantListitem(jsonRow.getString("Name"),jsonRow.getString("Number"),jsonRow.getString("ImageURL"),Double.toString(avg_rating_number),jsonRow.getString("cnt"));Items.add(item);
                                listView.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.e("error",error.toString());
                        //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Type","중식");//변경
                return params;
            }
        };
        queue.add(strRequest2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getActivity(),RestaurantActivity.class);
                intent.putExtra("Name",Items.get(i).getName());
                intent.putExtra("Number",Items.get(i).getR_number());
                startActivity(intent);
            }
        });

        return view;
    }

    public static Fragment_JungSik newInstance() {//변경

        return new Fragment_JungSik();//변경

    }
}
