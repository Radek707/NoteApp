package com.example.mynotes.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;
import com.example.mynotes.callbacks.ColorSelectorCallback;
import com.example.mynotes.models.viewmodels.NoteColorSelectorViewModel;

import java.util.List;

public class NoteColorAdapter extends RecyclerView.Adapter<NoteColorAdapter.NoteColorViewHolder>{
    private Context context;
    private List<NoteColorSelectorViewModel> noteColors;
    private NoteColorSelectorViewModel selectedColor;
    private ColorSelectorCallback callback;

    public NoteColorAdapter(Context context, List<NoteColorSelectorViewModel> noteColors, ColorSelectorCallback callback) {
        this.context = context;
        this.noteColors = noteColors;
        for (NoteColorSelectorViewModel colorViewModel : noteColors) {
            if (colorViewModel.isSelected()) {
                selectedColor = colorViewModel;
            }
        }
        this.callback = callback;
    }

    public NoteColorSelectorViewModel getSelectedColor() {
        return selectedColor;
    }

    @NonNull
    @Override
    public NoteColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteColorViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.note_color_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteColorViewHolder holder, int position) {
        bindNoteColor(holder, noteColors.get(position));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void bindNoteColor(NoteColorViewHolder holder,
                               NoteColorSelectorViewModel noteColorSelectorViewModel) {
            holder.colorView.setBackgroundColor(Color.parseColor(noteColorSelectorViewModel
                    .getNoteColor()
                    .getColorCode()));
            if (noteColorSelectorViewModel.isSelected()) {
                holder.colorContainer.setBackground(context.getResources()
                        .getDrawable(R.drawable.selected_item_background));
            } else {
                holder.colorContainer.setBackground(null);
            }
            holder.colorContainer.setOnClickListener(v -> selectNoteColor(noteColorSelectorViewModel));
    }

    private void selectNoteColor(NoteColorSelectorViewModel noteColorSelectorViewModel) {
        if(callback != null) {
            callback.onSelectColor(noteColorSelectorViewModel.getNoteColor());
        }
        if (selectedColor != null) {
            selectedColor.setSelected(false);
        }
        noteColorSelectorViewModel.setSelected(!noteColorSelectorViewModel.isSelected());
        selectedColor = noteColorSelectorViewModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noteColors.size();
    }

    static class NoteColorViewHolder extends RecyclerView.ViewHolder{
        View colorView;
        FrameLayout colorContainer;

        public NoteColorViewHolder(@NonNull View itemView) {
            super(itemView);
            colorView = itemView.findViewById(R.id.colorImageView);
            colorContainer = itemView.findViewById(R.id.colorContainerLayout);
        }
    }
}
