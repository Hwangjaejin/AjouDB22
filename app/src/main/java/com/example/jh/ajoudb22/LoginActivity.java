package com.example.jh.ajoudb22;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        final Button find_idButton = (Button) findViewById(R.id.find_idButton);
        final Button find_pwButton = (Button) findViewById(R.id.find_pwButton);

        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success =jsonResponse.getBoolean("success");
                            if(success){
                              /*  AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("로그인 성공!")
                                        .setPositiveButton("확인",new DialogInterface.OnClickListener(){
                                            public void onClick(DialogInterface dialog, int i){
                                                Intent intent = new Intent(LoginActivity.this,TemporaryActivity.class);
                                                LoginActivity.this.startActivity(intent);  *
                                            }
                                        })
                                        .create();
                                dialog.show();
*/
                                Toast.makeText(getApplicationContext(),"로그인 성공!",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this,TemporaryActivity.class);
                                LoginActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                dialog = builder.setMessage("로그인 실패!")
                                        .setNegativeButton("확인",null)
                                        .create();
                                dialog.show();
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID,userPassword,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        find_idButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this,Find_id_Activity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

        find_pwButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this,Find_pw_Activity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
    }



    //현재 Activity가 종료됐을 시
    @Override
    protected void onStop(){
        super.onStop();
        if(dialog != null)
        {
            dialog.dismiss();
            dialog = null;
        }//현재 dialog가 켜져있을 때 함부로 종료가 되지 않게 해주기 위함.
    }

}
