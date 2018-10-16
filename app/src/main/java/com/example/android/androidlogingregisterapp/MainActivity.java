package com.example.android.androidlogingregisterapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    private RelativeLayout relativeLayout;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relativeLayout.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = findViewById(R.id.relative1);

        handler.postDelayed(runnable, 2000);
    }

    public void handleLogin(View view) {
    }

    public void handleRegister(View view) {
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
}
