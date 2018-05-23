package com.example.jh.ajoudb22;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class TemporaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary);

        EditText idText = (EditText) findViewById(R.id.idText);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        idText.setText(userID);
    }
}
