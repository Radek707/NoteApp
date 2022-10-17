package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.Models.Note;
import com.example.mynotes.Repository.MyNotesRepository;
import com.example.mynotes.Repository.RepositoryProvider;

public class AddNoteActivity extends AppCompatActivity {

    public static final int NO_NOTE = -1;
    private Note note;
    private EditText titleEditText, detailsEditText;
    private MyNotesRepository myNotesRepository;
    private CheckBox archivedCheckBox;
    private long noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        myNotesRepository = RepositoryProvider.getInstance(this);

        titleEditText = findViewById(R.id.titleEditText);
        detailsEditText = findViewById(R.id.detailsEditText);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(view -> saveNote());
        Button deleteButton = findViewById(R.id.deleteButton);
        archivedCheckBox = findViewById(R.id.archavedCheckBox);

        Intent intent = getIntent();
        noteId = intent.getLongExtra(TAG.NOTE_ID, NO_NOTE);

        if (noteId != NO_NOTE) {
            deleteButton.setOnClickListener(view -> deleteNote());
            deleteButton.setVisibility(View.VISIBLE);
            note = myNotesRepository.getNoteById(noteId);
            updateUI(note);
        } else {
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    private void updateUI(Note note) {
        if (note != null) {
            titleEditText.setText(note.getTitle());
            detailsEditText.setText(note.getDetails());
            archivedCheckBox.setChecked(note.getArchived());
        }
    }

    private void deleteNote() {
        myNotesRepository.deleteNote(noteId);
        Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void saveNote() {
        String title = titleEditText.getText().toString();
        String details = detailsEditText.getText().toString();

        if (note == null) {
            note = new Note();
            note.setTitle(title);
            note.setDetails(details);
            note.setArchived(archivedCheckBox.isChecked());
            note.setIdUser(myNotesRepository.getLoggedInUserId());
            myNotesRepository.addNote(note);
        } else  {
            note.setTitle(title);
            note.setDetails(details);
            note.setArchived(archivedCheckBox.isChecked());
            myNotesRepository.editNote(note);
        }
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        finish();
    }
}