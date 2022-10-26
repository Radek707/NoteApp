package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.callbacks.ColorSelectorCallback;
import com.example.mynotes.custom.ColorSelectorCustomView;
import com.example.mynotes.models.Note;
import com.example.mynotes.models.NoteColor;
import com.example.mynotes.models.viewmodels.NoteColorSelectorViewModel;
import com.example.mynotes.repository.MyNotesRepository;
import com.example.mynotes.repository.RepositoryProvider;

import java.util.ArrayList;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity implements ColorSelectorCallback {

    public static final int NO_NOTE = -1;
    private Note note;
    private EditText titleEditText, detailsEditText;
    private MyNotesRepository myNotesRepository;
    private CheckBox archivedCheckBox;
    private long noteId;
    private ColorSelectorCustomView colorSelectorCustomView;

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

        List<NoteColor> noteColors = myNotesRepository.getNoteColors();
        List<NoteColorSelectorViewModel> colorViewModels = createColorViewModel(noteColors);

        if (noteId != NO_NOTE) {
            deleteButton.setOnClickListener(view -> deleteNote());
            deleteButton.setVisibility(View.VISIBLE);
            note = myNotesRepository.getNoteById(noteId);
            selectNoteColor(note, colorViewModels);
            updateUI(note);
        } else {
            deleteButton.setVisibility(View.INVISIBLE);
        }
        colorSelectorCustomView = findViewById(R.id.colorSelectorCustomView);
        colorSelectorCustomView.setCallback(this);
        colorSelectorCustomView.initializeColors(colorViewModels);
    }

    private void selectNoteColor(Note note, List<NoteColorSelectorViewModel> colorViewModels) {
        for (NoteColorSelectorViewModel colorViewModel:colorViewModels) {
            if (note.getColorId() == colorViewModel.getNoteColor().getId()) {
                colorViewModel.setSelected(true);
                break;
            }
        }
    }

    private List<NoteColorSelectorViewModel> createColorViewModel(List<NoteColor> noteColors) {
        List<NoteColorSelectorViewModel> list = new ArrayList<>();
        for (NoteColor noteColor :  noteColors) {
            list.add(new NoteColorSelectorViewModel(noteColor));
        }

        return list;
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
        NoteColorSelectorViewModel selectedColor = colorSelectorCustomView.getSelectedColor();
        if (selectedColor == null) {
            Toast.makeText(this, "Pick a color for note", Toast.LENGTH_SHORT).show();
            return;
        }

        if (note == null) {
            note = new Note();
            note.setTitle(title);
            note.setDetails(details);
            note.setArchived(archivedCheckBox.isChecked());
            note.setIdUser(myNotesRepository.getLoggedInUserId());
            note.setColorId(selectedColor.getNoteColor().getId());
            myNotesRepository.addNote(note);
        } else  {
            note.setTitle(title);
            note.setDetails(details);
            note.setArchived(archivedCheckBox.isChecked());
            note.setColorId(selectedColor.getNoteColor().getId());
            myNotesRepository.editNote(note);
        }
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onSelectColor(NoteColor noteColor) {
        Toast.makeText(this, "Selected color" + noteColor.getColorCode(), Toast.LENGTH_SHORT).show();
    }
}