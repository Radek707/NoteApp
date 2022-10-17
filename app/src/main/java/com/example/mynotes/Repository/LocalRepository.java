package com.example.mynotes.Repository;

import android.app.Activity;

import com.example.mynotes.Models.DaoSession;
import com.example.mynotes.Models.Note;
import com.example.mynotes.Models.NoteDao;
import com.example.mynotes.Models.User;
import com.example.mynotes.Models.UserDao;
import com.example.mynotes.MyNoteApp;
import com.example.mynotes.callbacks.LogInCallback;

import java.util.List;

public class LocalRepository implements MyNotesRepository {
    private DaoSession daoSession;
    private NoteDao noteDao;
    private UserDao userDao;
    private User loggedInUser;
    private static LocalRepository instance;

    private LocalRepository(Activity activity) {
        daoSession = ((MyNoteApp)activity.getApplication()).getDaoSession();
        noteDao = daoSession.getNoteDao();
        userDao = daoSession.getUserDao();
    }

    public static LocalRepository getInstance(Activity activity) {
        if (instance == null) {
            instance = new LocalRepository(activity);
        }
        return instance;
    }

    @Override
    public void addNote(Note note) {
        noteDao.insert(note);
        loggedInUser.getNotes().add(note);
    }

    @Override
    public void editNote(Note note) {
        noteDao.update(note);
    }

    @Override
    public void deleteNote(long id) {
        Note note = getNoteById(id);
        noteDao.delete(note);
    }

    @Override
    public Note getNoteById(long id) {
        return noteDao.getSession().load(Note.class, id);
    }

    @Override
    public List<Note> getAllNotes() {
        return loggedInUser.getNotes();
    }

    @Override
    public long getLoggedInUserId() {
        return loggedInUser.getId();
    }

    @Override
    public void register(User user) {
        userDao.insert(user);
    }

    @Override
    public void logIn(String userName, String password, LogInCallback logInCallback) {
        List<User> users = userDao.getSession().queryBuilder(User.class)
                .where(UserDao.Properties.Name.eq(userName)).build().list();

        if (users.size() == 0) {
            logInCallback.onFailure("User does not exist.");
        } else {
            User user = users.get(0);
            loggedInUser = user;
            if (password.equals(users.get(0).getPassword())) {
                logInCallback.onSuccess();
            } else {
                logInCallback.onFailure("Wrong password");
            }
        }
    }
}
