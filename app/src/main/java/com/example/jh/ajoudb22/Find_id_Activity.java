package com.example.jh.ajoudb22;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Find_id_Activity extends AppCompatActivity {

    private AlertDialog dialog;
    private String[] userID = new String[500];
    private String[] userEmail = new String[500];
    int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id_);

        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final Button button = (Button) findViewById(R.id.button2);


        Intent intent = getIntent();
        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            String []userID2 = new String[jsonArray.length()];
            String []userEmail2 = new String[jsonArray.length()];
            int count = 0;
            while(count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                userID2[count] = object.getString("userID");
                userEmail2[count] = object.getString("userEmail");
                userID[count] = userID2[count];
                userEmail[count] = userEmail2[count];
                count++;
                length++;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String userEmail3 = emailText.getText().toString();

                if (userEmail3.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Find_id_Activity.this);
                    dialog = builder.setMessage("E-mail을 입력해 주세요.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();

                }
                else {
                    int count = 0;
                    while (count < length) {
                        if (userEmail[count].equals(userEmail3))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Find_id_Activity.this);
                            dialog = builder.setMessage("ID : "+userID[count])
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialog, int i){
                                            Intent intent = new Intent(Find_id_Activity.this,LoginActivity.class);
                                            Find_id_Activity.this.startActivity(intent);
                                        }
                                    })
                                    .create();
                            dialog.show();

                            break;
                        }
                        count++;
                    }
                    if(count == length)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Find_id_Activity.this);
                        dialog = builder.setMessage("회원가입 되어 있지 않습니다.")
                                .setNegativeButton("확인",null)
                                .create();
                        dialog.show();
                    }
                }
            }
        });

    }

}
