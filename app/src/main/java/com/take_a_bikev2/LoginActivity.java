package com.take_a_bikev2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

/**
 * Created by David on 2/14/2017.
 */

public class LoginActivity extends AppCompatActivity{

    static EditText _emailText;
    static EditText _passwordText;
    static Button _loginButton;

    //email regex
    Pattern emailpattern = Pattern.compile("^[A-Za-z].*?@uncc\\.edu$");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText)findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validate();//when button is clicked; call login() method
            }

        });

    }

    public void login() {

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.SplashTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 1250);
    }


    public void onLoginSuccess() {
        Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true); //If the user is a student then open MainActivity
        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
        LoginActivity.this.startActivity(myIntent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed. Please use a school email", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public void validate() {

        //grabs credentials from user and stores them in String variables
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        //validates email and password
        if (email.isEmpty() || !emailpattern.matcher(email).matches()) {
            _emailText.setError("Please enter a valid UNCC email address.");
            onLoginFailed();
        } else {
            _emailText.setError(null);
            if (password.isEmpty() || password.length() < 8 || password.length() > 15 ) {
                _passwordText.setError("Password must be between 8 and 15 characters");
                onLoginFailed();
            } else {
                _passwordText.setError(null);
                login();
            }
        }
    }

}
