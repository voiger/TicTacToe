package com.uialert.tictactoe.screens.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.uialert.tictactoe.R;
import com.uialert.tictactoe.engine.ChangesEngine;
import com.uialert.tictactoe.engine.enumGame.TicTacToe;

public class PlayShowResultGame extends DialogFragment {
    TicTacToe win;
    ChangesEngine changesEngine;

    public PlayShowResultGame(TicTacToe win, ChangesEngine changesEngine) {
        this.win = win;
        this.changesEngine = changesEngine;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(inflater.getContext(), R.layout.show_result_game, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.textViewWinner);
        Button buttonRestart = view.findViewById(R.id.buttonAgainGame);
        Button buttonExitInMenu = view.findViewById(R.id.buttonComeBackMenu);
        switch (win){
            case cross:
                textView.setText("Победил крестик");
                break;
            case zero:
                textView.setText("Победил нолик");
                break;
            case empty:
                textView.setText("Ничья");
                break;
        }
        buttonExitInMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changesEngine.close();
            }
        });
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changesEngine.restartGame(win);
                getDialog().dismiss();
            }
        });
    }

}
