package com.uialert.tictactoe.screens.play;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.uialert.tictactoe.R;
import com.uialert.tictactoe.engine.enumGame.TicTacToe;

public class PlayShowResultGame extends DialogFragment {
    TicTacToe win;

    public PlayShowResultGame(TicTacToe win) {
        this.win = win;
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

    }
}
