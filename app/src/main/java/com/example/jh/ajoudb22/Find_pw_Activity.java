package com.example.jh.ajoudb22.jaejin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jh.ajoudb22.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class Find_pw_Activity extends AppCompatActivity {

    private AlertDialog dialog;
    private String[] userID = new String[500];
    private String[] userPassword = new String[500];
    int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw_);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final Button button = (Button) findViewById(R.id.button2);

        Intent intent = getIntent();
        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("userList"));
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            String []userID2 = new String[jsonArray.length()];
            String []userPassword2 = new String[jsonArray.length()];
            int count = 0;
            while(count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                userID2[count] = object.getString("userID");
                userPassword2[count] = object.getString("userPassword");
                userID[count] = userID2[count];
                userPassword[count] = userPassword2[count];
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
                final String userID3 = idText.getText().toString();

                if (userID3.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Find_pw_Activity.this);
                    dialog = builder.setMessage("ID를 입력해 주세요.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();

                }
                else {
                    int count = 0;
                    while (count < length) {
                        if (userID[count].equals(userID3))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(Find_pw_Activity.this);
                            dialog = builder.setMessage("Password : "+userPassword[count])
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialog, int i){
                                            Intent intent = new Intent(Find_pw_Activity.this,LoginActivity.class);
                                            Find_pw_Activity.this.startActivity(intent);
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
                        AlertDialog.Builder builder = new AlertDialog.Builder(Find_pw_Activity.this);
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
