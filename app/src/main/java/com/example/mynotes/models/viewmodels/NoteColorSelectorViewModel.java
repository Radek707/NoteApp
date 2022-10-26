package com.example.mynotes.models.viewmodels;

import com.example.mynotes.models.NoteColor;

public class NoteColorSelectorViewModel {
    private NoteColor noteColor;
    private boolean isSelected;

    public NoteColorSelectorViewModel(NoteColor noteColor) {
        this.noteColor = noteColor;
    }

    public NoteColor getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(NoteColor noteColor) {
        this.noteColor = noteColor;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
