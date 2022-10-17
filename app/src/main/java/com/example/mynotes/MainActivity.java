package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.mynotes.Models.Note;
import com.example.mynotes.Models.User;
import com.example.mynotes.Repository.MyNotesRepository;
import com.example.mynotes.Repository.RepositoryProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteClickListener{

    private MyNotesRepository myNotesRepository;
    private List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view -> openAddNoteActivity());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // get the note DAO
        myNotesRepository = RepositoryProvider.getInstance(this);

//        User user = new User();
//        user.setName("Radek");
//        user.setPassword("1");
//        myNotesRepository.register(user);
        noteList = myNotesRepository.getAllNotes();

        RecyclerView notesRecyclerView = findViewById(R.id.noteRecyclerView);
        notesRecyclerView.setAdapter(new NoteAdapter(this, noteList, this));
    }

    private void openAddNoteActivity() {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        long id = noteList.get(position).getId();
        intent.putExtra(TAG.NOTE_ID, id);
        startActivity(intent);
    }
}