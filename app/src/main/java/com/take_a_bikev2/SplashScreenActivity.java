package com.take_a_bikev2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by Kevin on 2/18/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        finish();
    }
}
