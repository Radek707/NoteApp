package com.example.mynotes.repository;

import android.app.Activity;

public class RepositoryProvider {
    public static MyNotesRepository getInstance(Activity activity) {
        return LocalRepository.getInstance(activity);
    }
}
