package com.uialert.tictactoe.screens.play;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.uialert.tictactoe.R;
import com.uialert.tictactoe.engine.ChangesEngine;

public class PlayMenu extends DialogFragment {
    ChangesEngine changesEngine;

    public PlayMenu(ChangesEngine changesEngine) {
        this.changesEngine = changesEngine;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(inflater.getContext(), R.layout.menu_play,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button closeMenu = view.findViewById(R.id.buttonCloseMenu);
        Button exitInMenu = view.findViewById(R.id.buttonExitInMenu);
        exitInMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changesEngine.close();
            }
        });
        closeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }
}
