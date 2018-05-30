package com.example.jh.ajoudb22;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.floor;

public class RestaurantActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView Toolbar_Text;
    private TextView Review_Num_Text;
    private TextView Rating_num_Text;
    private RatingBar ratingBar;
    private TextView R_Description_Text;
    private TextView R_Phone_num_text;
    private TextView R_Address_text;
    private ListView Listview_menu;

    private LinearLayout review_btn;
    private LinearLayout rating_btn;
    private LinearLayout header_btn;
    private Button map_btn;

    private int UserID=1;
    private int R_Number=1;
    private String R_Name;
    private String Phone_number;
    private String Description;
    private String Address;

    private ArrayList<MenuListitem> Items;
    private ListviewAdapter adapter;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        Intent intent=getIntent();
        String s=intent.getStringExtra("Name");
        Log.e("Name",s);
        R_Number=Integer.parseInt(intent.getStringExtra("Number"));

        view=getLayoutInflater().inflate(R.layout.header,null,false);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        Toolbar_Text=(TextView)findViewById(R.id.Toolbar_text);
        Review_Num_Text=(TextView)view.findViewById(R.id.review_num);
        Rating_num_Text=(TextView)view.findViewById(R.id.rating_num);
        ratingBar=(RatingBar)view.findViewById(R.id.ratingbar);
        R_Description_Text=(TextView)view.findViewById(R.id.R_Description);
        R_Phone_num_text=(TextView)view.findViewById(R.id.R_Phone_number);
        R_Address_text=(TextView)view.findViewById(R.id.R_Address);
        Listview_menu=(ListView) findViewById(R.id.listview_menu);

        review_btn=(LinearLayout)view.findViewById(R.id.review_btn);
        rating_btn=(LinearLayout)view.findViewById(R.id.rating_btn);
        header_btn=(LinearLayout)view.findViewById(R.id.header_btn);
        map_btn=(Button)view.findViewById(R.id.map_btn);

        Items=new ArrayList<>();


        Listview_menu.addHeaderView(view);


        adapter=new ListviewAdapter(this,R.layout.item_menu,Items);
        //Listview_menu.setAdapter(adapter);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.button_back);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://jaejindb.cafe24.com/RestaurantInfo.php";
        String url2 ="http://jaejindb.cafe24.com/Menu.php";
        String url3 ="http://jaejindb.cafe24.com/Review_Rate.php";

        StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONArray jsonArray=new JSONArray(response.toString());
                            JSONObject jsonRow = jsonArray.getJSONObject(0);

                            R_Name=jsonRow.getString("Name");
                            Phone_number=jsonRow.getString("Phone_number");
                            Description=jsonRow.getString("Description");
                            Address=jsonRow.getString("Address");

                            Toolbar_Text.setText(R_Name);
                            R_Description_Text.setText(Description);
                            R_Phone_num_text.setText(Phone_number);
                            R_Address_text.setText(Address);

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
                Log.d("number",""+R_Number);
                params.put("R_Number",""+R_Number);

                return params;
            }
        };
        queue.add(strRequest);

        StringRequest strRequest2 = new StringRequest(Request.Method.POST, url2,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONArray jsonArray=new JSONArray(response.toString());

                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonRow = jsonArray.getJSONObject(i);
                                MenuListitem item=new MenuListitem(jsonRow.getString("M_name"),jsonRow.getString("Price"));
                                Items.add(item);
                                Listview_menu.setAdapter(adapter);
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
                Log.d("number",""+R_Number);
                params.put("R_Number",""+R_Number);

                return params;
            }
        };
        queue.add(strRequest2);



        StringRequest strRequest3 = new StringRequest(Request.Method.POST, url3,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONArray jsonArray=new JSONArray(response.toString());
                            JSONObject jsonRow = jsonArray.getJSONObject(0);

                            String avg_rating=jsonRow.getString("avg");
                            double avg_rating_number=Double.parseDouble(avg_rating);
                            avg_rating_number=(floor(10*avg_rating_number))/10;

                            Review_Num_Text.setText(jsonRow.getString("cnt"));
                            Rating_num_Text.setText(Double.toString(avg_rating_number));
                            ratingBar.setRating(Float.parseFloat(jsonRow.getString("avg")));
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
                Log.d("number",""+R_Number);
                params.put("R_Number",""+R_Number);

                return params;
            }
        };
        queue.add(strRequest3);



        review_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RestaurantActivity.this,ReviewActivity.class);
                intent.putExtra("R_number",R_Number);
                intent.putExtra("User_id",UserID);
                startActivity(intent);
                Log.d("touch","true");
            }
        });
        rating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RestaurantActivity.this,ReviewActivity.class);
                intent.putExtra("R_number",R_Number);
                intent.putExtra("User_id",UserID);
                startActivity(intent);
            }
        });
        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RestaurantActivity.this,MapActivity.class);
                intent.putExtra("R_number",R_Number);
                startActivity(intent);
            }
        });

        header_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
