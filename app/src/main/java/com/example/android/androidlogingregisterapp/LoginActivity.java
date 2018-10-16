package com.example.android.androidlogingregisterapp;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    // handle animation for splash page
    private RelativeLayout relativeLayout;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relativeLayout.setVisibility(View.VISIBLE);
        }
    };


    private EditText inputEmail, inputPassword;
    private Button btnLogin;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        // user already exist
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);

        relativeLayout = findViewById(R.id.relative1);
        handler.postDelayed(runnable, 2000);

        inputEmail = findViewById(R.id.email_et);
        inputPassword = findViewById(R.id.password_et);
        btnLogin = findViewById(R.id.login_btn);

        // Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please, Enter email address!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please, Enter password!", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Please, Enter valid Email and Password", Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });


    }

    public void handleRegister(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
}
