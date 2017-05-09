package com.take_a_bikev2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by David on 2/14/2017
 * Made the Login activity
 *
 * Edited by jesus on 4/1/17.
 * Moved the Login activity into a fragment
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    //Tag for reference
    private static final String TAG = "LoginFragment";

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    Pattern emailpattern = Pattern.compile("^[A-Za-z].*?@uncc\\.edu$");
    private FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);

        _emailText = (EditText) view.findViewById(R.id.input_email);
        _passwordText = (EditText) view.findViewById(R.id.input_password);
        _loginButton = (Button) view.findViewById(R.id.btn_login);
        auth = FirebaseAuth.getInstance(); //Firebase

        //For testing
        _emailText.setText("dgreen88@uncc.edu");
        _passwordText.setText("password");

        _loginButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view){

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
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(getContext(), "Login FAILED", Toast.LENGTH_LONG).show();
                        } else {
                            login();
                        }
                    }
                });
    }

    public void login() {

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true); //If the user is a student then open MapsActivity
        Intent myIntent = new Intent(getActivity(), MapsActivity.class);
        LoginFragment.this.startActivity(myIntent);
        getActivity().finish();
    }
}
