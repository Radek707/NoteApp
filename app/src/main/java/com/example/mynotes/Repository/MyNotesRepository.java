package com.example.mynotes.Repository;

import com.example.mynotes.Models.Note;
import com.example.mynotes.Models.User;
import com.example.mynotes.callbacks.LogInCallback;

import java.util.List;

public interface MyNotesRepository {
    void addNote(Note note);
    void editNote(Note note);
    void deleteNote(long id);
    Note getNoteById(long id);
    List<Note> getAllNotes();
    long getLoggedInUserId();

    void register(User user);
    void logIn(String userName, String password, LogInCallback logInCallback);
}
