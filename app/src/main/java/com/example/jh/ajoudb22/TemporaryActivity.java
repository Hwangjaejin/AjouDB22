package com.example.jh.ajoudb22;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class TemporaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary);

        final ImageButton hanSik_Button = (ImageButton) findViewById(R.id.hanSik_Button);
        final ImageButton yangSik_Button = (ImageButton) findViewById(R.id.yangSik_Button);
        final ImageButton jungSik_Button = (ImageButton) findViewById(R.id.jungSik_Button);
        final ImageButton ilSik_Button = (ImageButton) findViewById(R.id.ilSik_Button);
        final ImageButton chicken_Button = (ImageButton) findViewById(R.id.chicken_Button);
        final ImageButton hamburger_Button = (ImageButton) findViewById(R.id.hamburger_Button);

        Intent intent = getIntent();

        final String userID = intent.getStringExtra("userID");

        hanSik_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextActivity(userID, "0");
            }
        });
        yangSik_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextActivity(userID, "1");
            }
        });
        jungSik_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextActivity(userID, "2");
            }
        });
        ilSik_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextActivity(userID, "3");
            }
        });
        chicken_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextActivity(userID, "4");
            }
        });
        hamburger_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callNextActivity(userID, "5");
            }
        });
    }

    public void callNextActivity(String userID, String tab_id){
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("tab_id", tab_id);

        startActivity(intent);
    }
}
