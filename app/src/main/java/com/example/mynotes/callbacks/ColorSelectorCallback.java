package com.example.mynotes.callbacks;

import com.example.mynotes.models.NoteColor;
import com.example.mynotes.models.viewmodels.NoteColorSelectorViewModel;

public interface ColorSelectorCallback {
    void onSelectColor(NoteColor noteColor);
}
