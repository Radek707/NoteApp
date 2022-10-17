package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.Repository.MyNotesRepository;
import com.example.mynotes.Repository.RepositoryProvider;
import com.example.mynotes.callbacks.LogInCallback;
import com.google.android.material.textfield.TextInputLayout;

public class LogInActivity extends AppCompatActivity implements LogInCallback {

    private TextInputLayout usernameTextInputLayout, passwordTextInputLayout;
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        findViewById(R.id.registerButton).setOnClickListener(v -> register());
        findViewById(R.id.logInButton).setOnClickListener(
                v -> tryLogIn(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString()));

        setUpUI();
    }

    private void register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void tryLogIn(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {
            usernameEditText.requestFocus();
            usernameTextInputLayout.setError("Username cannot be empty");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.requestFocus();
            passwordTextInputLayout.setError("Password cannot be empty");
            return;
        }

        MyNotesRepository notesRepository = RepositoryProvider.getInstance(this);
        notesRepository.logIn(userName, password, this);
    }

    private void setUpUI() {
        usernameTextInputLayout = findViewById(R.id.userNameTextInputLayout);
        passwordTextInputLayout = findViewById(R.id.passwordTextInputLayout);
        usernameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }
}