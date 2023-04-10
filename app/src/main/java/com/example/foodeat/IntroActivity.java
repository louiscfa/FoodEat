package com.example.foodeat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        Button button = findViewById(R.id.startBtn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(IntroActivity.this, MainActivity.class));

    }
}