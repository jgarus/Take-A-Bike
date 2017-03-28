package com.take_a_bikev2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

/**
 * Created by David on 2/14/2017.
 */

public class LoginActivity extends AppCompatActivity{

    static EditText _emailText;
    static EditText _passwordText;
    static Button _loginButton;
    Pattern emailpattern = Pattern.compile("^[A-Za-z].*?@uncc\\.edu$");
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);
        auth = FirebaseAuth.getInstance(); //Firebase

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _emailText.getText().toString();
                final String password = _passwordText.getText().toString();

                //validates email and password
                if (email.isEmpty() || !emailpattern.matcher(email).matches()) {
                    _emailText.setError("Please enter a valid UNCC email address.");
                    return;
                }
                if (password.isEmpty() || password.length() < 8 || password.length() > 15 ) {
                    _passwordText.setError("Password must be between 8 and 15 characters");
                    return;
                }

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                        Toast.makeText(getBaseContext(), "Login FAILED", Toast.LENGTH_LONG).show();
                                } else {
                                    login();
                                }
                            }
                        });
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

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 1250);
    }

    public void onLoginSuccess() {
        Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true); //If the user is a student then open MainActivity
        Intent myIntent = new Intent(LoginActivity.this, MapsActivity.class);
        LoginActivity.this.startActivity(myIntent);
        finish();
    }

}
