package com.uialert.tictactoe.screens.play;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.uialert.tictactoe.R;
import com.uialert.tictactoe.engine.ChangesEngine;
import com.uialert.tictactoe.engine.Engine;
import com.uialert.tictactoe.engine.enumGame.TicTacToe;

public class PlayActivity extends AppCompatActivity implements ChangesEngine {
    LinearLayout board;

    int cross = 0,zero = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        board = findViewById(R.id.TicTacToeBoard);
        board.setOrientation(LinearLayout.VERTICAL);
        Engine engine = new Engine(board, this);
        engine.startGame();

    }

    public void startMenu(View view) {
        DialogFragment dialogFragment = new PlayMenu();
        dialogFragment.show(getSupportFragmentManager(),"Play_menu");
    }

    @Override
    public void close() {
        finish();
    }


    @Override
    public void win(TicTacToe win) {
        switch (win){
            case cross:
                break;
            case zero:
                break;
            case empty:
                break;
        }

    }
}