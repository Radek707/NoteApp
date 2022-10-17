package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.mynotes.Models.User;
import com.example.mynotes.Repository.MyNotesRepository;
import com.example.mynotes.Repository.RepositoryProvider;

public class RegisterActivity extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEditText = findViewById(R.id.userNameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        findViewById(R.id.registerButton).setOnClickListener(v -> register());
    }

    private void register() {
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        User user = new User();
        user.setName(userName);
        user.setPassword(password);

        MyNotesRepository notesRepository = RepositoryProvider.getInstance(this);
        notesRepository.register(user);

        finish();
    }
}