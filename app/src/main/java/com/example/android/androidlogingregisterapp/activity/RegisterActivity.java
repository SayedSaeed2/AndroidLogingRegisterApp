package com.example.android.androidlogingregisterapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.androidlogingregisterapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * RegisterActivity represents creating new account page if the user doesn't has any before
 * then store it's data in Firebae
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputCPassword;
    private Button btnRegister;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        inputEmail = findViewById(R.id.email_et_reg);
        inputPassword = findViewById(R.id.password_et_reg);
        inputCPassword = findViewById(R.id.c_password_et_reg);
        btnRegister = findViewById(R.id.btn_reg);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String cPassword = inputCPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please, enter email address", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password) && password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Please, enter password and it should be greater than 6 chars !", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(cPassword)) {
                    Toast.makeText(getApplicationContext(), "Please, enter confirm password", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!password.equals(cPassword)) {
                    Toast.makeText(getApplicationContext(), "Two Passwords doesn't match", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getApplicationContext(), "createUserWithEmail:onComplete:" + task.isSuccessful(),
                                        Toast.LENGTH_LONG).show();

                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Authentication failed." + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });

    }


    /**
     * Handle login button to go to the login page if he already have account
     *
     * @param view
     */
    public void handleLoginButton(View view) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

}
