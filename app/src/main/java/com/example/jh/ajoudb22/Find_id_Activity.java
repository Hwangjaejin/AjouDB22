package com.example.jh.ajoudb22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Find_id_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id_);

        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final Button button = (Button) findViewById(R.id.button2);
    }
}
