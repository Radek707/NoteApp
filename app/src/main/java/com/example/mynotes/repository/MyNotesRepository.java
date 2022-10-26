package com.example.mynotes.repository;

import com.example.mynotes.models.Note;
import com.example.mynotes.models.NoteColor;
import com.example.mynotes.models.User;
import com.example.mynotes.callbacks.LogInCallback;

import java.util.List;

public interface MyNotesRepository {
    void addNote(Note note);
    void editNote(Note note);
    void deleteNote(long id);
    Note getNoteById(long id);
    List<Note> getAllNotes();
    long getLoggedInUserId();
    List<NoteColor> getNoteColors();

    void register(User user);
    void logIn(String userName, String password, LogInCallback logInCallback);
}
