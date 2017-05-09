package com.take_a_bikev2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            loginStart();
        }

    }

    //Start the login fragment
    public void loginStart(){
        LoginFragment login = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, login)
                .commit();
    }
}
