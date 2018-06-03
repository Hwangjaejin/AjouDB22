package com.example.jh.ajoudb22;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView Toolbar_Text;
    private RecyclerView mReviewView;
    private ReviewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private int R_num;
    private String R_name;
    private String R_number;
    private String R_id;
    private boolean reviewState = false;

    ArrayList<ReviewContent> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        toolbar=(Toolbar) findViewById(R.id.toolbar);
        Toolbar_Text=(TextView)findViewById(R.id.Toolbar_text);
        Intent intent = getIntent();
        R_num = intent.getIntExtra("R_number", 0);
        R_name = intent.getStringExtra("Name");
        R_id = intent.getStringExtra("User_id");

        R_number = String.valueOf(R_num);
        Toolbar_Text.setText(R_name);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김

    }

    @Override
    protected void onResume() {
        super.onResume();

        data.clear();
        ReviewData task = new ReviewData();
        task.execute(R_number);

        // RecyclerView binding
        mReviewView = findViewById(R.id.mList);
        // init LayoutManager

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // 기본값이 VERTICAL
        // setLayoutManager
        mReviewView.setLayoutManager(mLayoutManager);

        findViewById(R.id.reviewButton).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        if(!reviewState) {
                            Intent intent = new Intent(ReviewActivity.this, WriteActivity.class);
                            intent.putExtra("R_id", R_id);
                            intent.putExtra("R_number", R_number);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "리뷰는 한 번만 등록할 수 있습니다!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
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

    class ReviewData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String temp;

            try {
                JSONObject json = new JSONObject(result);
                temp = json.getString("response");
                JSONArray ary = new JSONArray(temp);

                for(int i = 0; i < ary.length(); i++) {
                    JSONObject js = ary.getJSONObject(i);
                    if(js.getString("R_id").equals(R_id)) {
                        reviewState = true;
                    } else {

                    }
                    data.add(new ReviewContent(js.getString("R_id") + " 님", Float.parseFloat(js.getString("Score")), js.getString("Date"), js.getString("Text")));
                }

                // init Adapter
                mAdapter = new ReviewAdapter();
                // set Data
                mAdapter.setData(data);
                // set Adapter
                mReviewView.setAdapter(mAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String R_number = (String)params[0];

            String serverURL = "http://jaejindb.cafe24.com/Review.php"; // 주소
            String postParameters = "R_number=" + R_number;

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                bufferedReader.close();
                return sb.toString();

            } catch (Exception e) {

                return new String("Error: " + e.getMessage());
            }
        }
    }
}

class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private ArrayList<ReviewContent> reviewData;

    public void setData(ArrayList<ReviewContent> list){
        reviewData = list;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 사용할 아이템의 뷰를 생성해준다.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_content, parent, false);

        ReviewViewHolder holder = new ReviewViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        ReviewContent data = reviewData.get(position);

        holder.name.setText(data.getName());
        holder.date.setText(data.getDate());
        holder.rating.setRating(data.getRating());
        holder.review.setText(data.getReview());

    }

    @Override
    public int getItemCount() {
        return reviewData.size();
    }

}

class ReviewViewHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView date;
    public RatingBar rating;
    public TextView review;

    public ReviewViewHolder(View reviewView) {
        super(reviewView);

        name = reviewView.findViewById(R.id.mName);
        date = reviewView.findViewById(R.id.mDate);
        rating = reviewView.findViewById(R.id.ratingBar);
        review = reviewView.findViewById(R.id.mReview);
    }
}