package com.example.bankapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3)); // splash screen staying duraion

        // Move Splassh screen to MainActivity
        Intent intent = new Intent(getApplicationContext(), BankListActivity.class);
        startActivity(intent);
        finish();

    }


}
