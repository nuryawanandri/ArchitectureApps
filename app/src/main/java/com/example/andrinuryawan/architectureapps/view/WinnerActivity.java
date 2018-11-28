package com.example.andrinuryawan.architectureapps.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.andrinuryawan.architectureapps.R;

public class WinnerActivity extends AppCompatActivity {

    private TextView winnerMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        winnerMsg = findViewById(R.id.winnerText);

        String dataIntent = getIntent().getExtras().getString("msg");

        winnerMsg.setText(dataIntent);

        System.out.println(dataIntent);
    }
}
