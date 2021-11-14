package com.uialert.tictactoe.engine;

import com.uialert.tictactoe.engine.enumGame.TicTacToe;

public interface ChangesEngine {
    void close();
    void win(TicTacToe win);
    void restartGame(TicTacToe win);
}
