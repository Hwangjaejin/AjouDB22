package com.example.jh.ajoudb22;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView Toolbar_Text;
    TextView tv;
    TextView rt;
    RatingBar rb;
    EditText et;

    private String R_id;
    private String R_number;
    String Text;
    String Score;
    String Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        Intent intent = getIntent();
        R_id = intent.getStringExtra("R_id");
        R_number = intent.getStringExtra("R_number");
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        Toolbar_Text=(TextView)findViewById(R.id.Toolbar_text);
        tv = (TextView) findViewById(R.id.mScoreText);
        rt = (TextView) findViewById(R.id.mRatingScore);
        rb = (RatingBar) findViewById(R.id.reviewRating);
        et = (EditText) findViewById(R.id.et);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생김
        //actionBar.setHomeAsUpIndicator(R.drawable.button_back);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rt.setText(Float.toString(rating));
            }
        });

        findViewById(R.id.regButton).setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) { // 등록

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                        Text = et.getText().toString();
                        Score = rt.getText().toString();
                        WriteReviewData task = new WriteReviewData();
                        task.execute(R_id, R_number, Text, Score, Time);
                    }
                }
        );
    }

    class WriteReviewData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            boolean temp;

            try {
                JSONObject json = new JSONObject(result);
                temp = json.getBoolean("response");
                if(temp) {
                    Toast.makeText(getApplicationContext(), "리뷰가 등록되었습니다!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "리뷰 등록 실패!", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            String R_id = (String)params[0];
            String R_number = (String)params[1];
            String Text = (String)params[2];
            String Score = (String)params[3];
            String Date = (String)params[4];

            String serverURL = "http://jaejindb.cafe24.com/WriteReview.php"; // 주소
            String postParameters = "R_id=" + R_id + "&R_number=" + R_number + "&Text=" + Text + "&Score=" + Score + "&Date=" +  Date;

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
