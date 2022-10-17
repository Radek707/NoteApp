package com.example.mynotes.Repository;

import android.app.Activity;

public class RepositoryProvider {
    public static MyNotesRepository getInstance(Activity activity) {
        return LocalRepository.getInstance(activity);
    }
}
