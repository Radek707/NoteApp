package com.example.mynotes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.Models.Note;
import com.example.mynotes.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private Context context;
    private List<Note> noteList;
    private OnNoteClickListener mOnNoteClickListener;

    public NoteAdapter(Context context, List<Note> noteList, OnNoteClickListener onNoteClickListener) {
        this.context = context;
        this.noteList = noteList;
        this.mOnNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.note_item_layout, parent, false), mOnNoteClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.detailsTextView.setText(note.getDetails());
        if (note.getArchived()) {
            holder.archivedImageView.setVisibility(View.VISIBLE);
        } else {
            holder.archivedImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTextView;
        TextView detailsTextView;
        View notesItemConstraintLayout;
        View archivedImageView;

        OnNoteClickListener onNoteClickListener;

        public NoteViewHolder(@NonNull View itemView, OnNoteClickListener onNoteClickListener) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            detailsTextView = itemView.findViewById(R.id.detailsTextView);
            notesItemConstraintLayout = itemView.findViewById(R.id.notesItemConstraintLayout);
            archivedImageView = itemView.findViewById(R.id.archivedImageView);

            this.onNoteClickListener = onNoteClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteClickListener.onClick(getAdapterPosition());
        }
    }

    public interface OnNoteClickListener {
        void onClick(int position);
    }
}
