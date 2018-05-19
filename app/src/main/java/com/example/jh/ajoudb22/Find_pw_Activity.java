package com.example.jh.ajoudb22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Find_pw_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw_);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final Button button = (Button) findViewById(R.id.button2);
    }
}
