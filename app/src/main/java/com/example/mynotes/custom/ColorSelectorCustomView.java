package com.example.mynotes.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;
import com.example.mynotes.adapters.NoteColorAdapter;
import com.example.mynotes.callbacks.ColorSelectorCallback;
import com.example.mynotes.models.viewmodels.NoteColorSelectorViewModel;

import java.util.List;

public class ColorSelectorCustomView extends FrameLayout {
    private RecyclerView colorSelectorRecyclerView;
    private TextView labelTextView;
    private NoteColorAdapter noteColorAdapter;
    private ColorSelectorCallback callback;

    public ColorSelectorCustomView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ColorSelectorCustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ColorSelectorCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public ColorSelectorCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(
                R.layout.color_selector_layout,
                this,
                true);
    }

    public void setCallback(ColorSelectorCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        colorSelectorRecyclerView = findViewById(R.id.colorsRecyclerView);
        labelTextView = findViewById(R.id.labelTextView);
    }

    public void initializeColors(List<NoteColorSelectorViewModel> colorSelectorViewModels) {
        noteColorAdapter = new NoteColorAdapter(getContext(), colorSelectorViewModels, callback);
        colorSelectorRecyclerView.setAdapter(noteColorAdapter);
    }

    public NoteColorSelectorViewModel getSelectedColor() {
        return noteColorAdapter.getSelectedColor();
    }
}
