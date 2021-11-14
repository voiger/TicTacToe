package com.uialert.tictactoe.screens.play;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uialert.tictactoe.R;
import com.uialert.tictactoe.engine.ChangesEngine;
import com.uialert.tictactoe.engine.Engine;
import com.uialert.tictactoe.engine.enumGame.TicTacToe;

public class PlayActivity extends AppCompatActivity implements ChangesEngine {
    LinearLayout board;
    Engine engine;
    int cross = 0,zero = 0;
    TextView textViewScoreO;
    TextView textViewScoreX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        board = findViewById(R.id.TicTacToeBoard);
        board.setOrientation(LinearLayout.VERTICAL);

        textViewScoreO = findViewById(R.id.textViewScoreO);

        textViewScoreX = findViewById(R.id.textViewScoreX);
        engine = new Engine(board, this,this);
        engine.startGame();

    }

    public void startMenu(View view) {
        DialogFragment dialogFragment = new PlayMenu(this);
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
                cross++;
                textViewScoreX.setText("X\n"+cross);
                break;
            case zero:
                zero++;
                textViewScoreO.setText("O\n"+zero);
                break;
            case empty:
                break;
        }
        restartGame(win);
        if(cross >= 5 || zero >= 5){
            startMenuWinner(win);
        }
    }

    @Override
    public void restartGame(TicTacToe win) {
        engine.restartGame(win == TicTacToe.cross ? TicTacToe.zero : TicTacToe.cross);

    }

    private void startMenuWinner(TicTacToe win){
        DialogFragment dialogFragment = new PlayShowResultGame(win,this);
        dialogFragment.show(getSupportFragmentManager(),"menu_winner");
        cross = 0;
        zero = 0;
    }
}