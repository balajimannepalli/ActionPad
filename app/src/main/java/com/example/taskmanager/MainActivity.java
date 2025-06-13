package com.example.taskmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText email, password;
    private Button loginb,registerb;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginb = findViewById(R.id.loginb);
        registerb = findViewById(R.id.registerb);
        mAuth = FirebaseAuth.getInstance();

        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (!isValidEmail(emailid)) {
                    Toast.makeText(MainActivity.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (emailid.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fill in email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                signInWithFirebase(emailid, pass);
            }
        });
        registerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
    private void registerUser() {
        String emailid = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (TextUtils.isEmpty(emailid)) {
            email.setError("Email required");
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            password.setError("Password required");
            return;
        }
        if (pass.length() < 6) {
            password.setError("Password must be atleast 6 characters");
            return;
        }
        mAuth.createUserWithEmailAndPassword(emailid, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(MainActivity.this, "Registration successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void signInWithFirebase(String emailid, String pass) {
        mAuth.signInWithEmailAndPassword(emailid, pass)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("loggedIn", true);
                            editor.apply();

                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, TasksActivity.class));
                            finish();
                        } else {
                            String errorMessage = "Login failed: ";
                            if (task.getException() instanceof FirebaseAuthException) {
                                errorMessage += ((FirebaseAuthException) task.getException()).getErrorCode();
                            } else {
                                errorMessage += "Error";
                            }
                            Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return Pattern.matches(emailPattern, email);
    }
}
