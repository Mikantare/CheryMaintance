package com.example.cherymaintance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference usersDataBaseReference;

    private TextInputLayout textInputEmail, textInputPassword, textComfirmPassword;
    private Button loginSignInButton;
    private TextView toogleLoginSignUpTextView;

    private boolean isLogInModeActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        usersDataBaseReference = database.getReference().child("users");

        textInputEmail = findViewById(R.id.textInputEmail);
        textInputPassword = findViewById(R.id.textInputPassword);
        textComfirmPassword = findViewById(R.id.textComfirmPassword);
        loginSignInButton = findViewById(R.id.loginSignInButton);
        toogleLoginSignUpTextView = findViewById(R.id.toogleLoginSignUpTextView);
    }


    private boolean validateEmail() {
        String emailInput = getEmail();
        if (emailInput.isEmpty()) {
            textInputEmail.setError(getString(R.string.empty_email_error));
            return false;
        } else {
            textInputEmail.setError("");
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = getPassword();
        String passwordConfirmInput = textComfirmPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError(getString(R.string.empty_password_error));
            return false;
        } else if (passwordInput.length() < 7) {
            textInputPassword.setError(getString(R.string.pasword_lenght_error));
            return false;
        } else if (!passwordInput.equals(passwordConfirmInput)) {
            textInputPassword.setError(getString(R.string.password_not_math_error));
            return false;
        } else {
            textInputPassword.setError("");
            return true;
        }
    }

    private boolean validatePasswordToLogin() {
        String passwordInput = getPassword();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError(getString(R.string.empty_password_error));
            return false;
        } else if (passwordInput.length() < 7) {
            textInputPassword.setError(getString(R.string.pasword_lenght_error));
            return false;
        } else {
            textInputPassword.setError("");
            return true;
        }
    }

    private String getEmail() {
        return textInputEmail.getEditText().getText().toString().trim();
    }

    private String getPassword() {
        return textInputPassword.getEditText().getText().toString().trim();
    }


    public void loginSignUpUser(View view) {
        String emailInput = getEmail();
        String passwordInput = getPassword();


    }

    public void toogleLoginSignUpUser(View view) {
        if (isLogInModeActive) {
            isLogInModeActive = false;
            loginSignInButton.setText("Sign Up");
        }
    }
}