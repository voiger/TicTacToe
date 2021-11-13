package com.uialert.tictactoe.engine;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import com.uialert.tictactoe.engine.enumGame.Players;
import com.uialert.tictactoe.engine.enumGame.TicTacToe;
import com.uialert.tictactoe.engine.ai.MinMax;
import com.uialert.tictactoe.screens.play.PlayShowResultGame;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;

public class Engine {
    Context context;
    static int SIZE_BOARD = 3;
    BoardField[][] boardFieldsArray = new BoardField[SIZE_BOARD][SIZE_BOARD];
    TicTacToe moveTicTacToe = TicTacToe.empty;
    ArrayList<BoardField> crossArray = new ArrayList<>();
    ArrayList<BoardField> zeroArray = new ArrayList<>();
    Map<BoardField, Coordinates> coordinatesByField = new IdentityHashMap<>();

    public static Players players;

    public Engine(LinearLayout board, Context context) {
        this.context = context;
        generateBoard(board);
        generateOnClick();
    }

    public void startGame() {
        moveTicTacToe = TicTacToe.cross;
    }

    private void generateOnClick() {
        for (int x = 0; x < boardFieldsArray.length; x++) {
            for (int y = 0; y < boardFieldsArray[x].length; y++) {
                boardFieldsArray[x][y].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickBoard(v);
                    }
                });
            }
        }
    }

    private void clickBoard(View v) {
        BoardField boardField = (BoardField) v;

        if (moveTicTacToe == TicTacToe.empty) {//блочим игру при победы
            return;
        }
        if (boardField.getTicTacToe() == TicTacToe.empty) {
            boardField.setTicTacToe(moveTicTacToe);

            if (boardField.getTicTacToe() == TicTacToe.zero) {
                zeroArray.add(boardField);
            } else {
                crossArray.add(boardField);
            }

            if (!checkFinish(moveTicTacToe, new GameState(boardFieldsArray, moveTicTacToe, crossArray, zeroArray, coordinatesByField))) {
                moveTicTacToe = moveTicTacToe == TicTacToe.cross ? TicTacToe.zero : TicTacToe.cross;
                if (players == Players.One) {
                    bot();
                }
            } else {
                win(moveTicTacToe);
            }
        }

    }

    private void win(TicTacToe moveTicTacToe) {
        DialogFragment dialogFragment = new PlayShowResultGame(moveTicTacToe);
        dialogFragment.show(context.start);
        //Toast.makeText(context, "Победитель " + (moveTicTacToe == TicTacToe.zero ? "Нолик" : "Крестик"), Toast.LENGTH_SHORT).show();
        this.moveTicTacToe = TicTacToe.empty;
    }

    static char player = 'X';
    static char opponent = '0';

    private void bot() {
        Log.e("bot", "bot: " + moveTicTacToe);
        ArrayList<BoardField> freeBoard = new ArrayList<>();
        for (int x = 0; x < boardFieldsArray.length; x++) {
            for (int y = 0; y < boardFieldsArray[x].length; y++) {
                if (boardFieldsArray[x][y].getTicTacToe() == TicTacToe.empty) {
                    freeBoard.add(boardFieldsArray[x][y]);
                }
            }
        }
        if (freeBoard.size() == 0) {
            return;
        }

        int maxScore = -2000;
        BoardField maxBoard = null;
        GameState gameState = new GameState(boardFieldsArray, moveTicTacToe, crossArray, zeroArray, coordinatesByField);


        for (BoardField boardField : freeBoard) {
            Coordinates coordinates = coordinatesByField.get(boardField);


            BoardField[][] boardFields = gameState.getBoardFieldsArray();
            char[][] board = new char[boardFields.length][boardFields.length];
            for (int x = 0; x < boardFields.length; x++) {
                for (int y = 0; y < boardFields[x].length; y++) {
                    if (x == coordinates.getX() && y == coordinates.getY()) {
                        board[x][y] = player;

                    } else {
                        board[x][y] = boardFields[x][y].getChar();
                    }

                }

                int score = MinMax.minMaxStateGame(board, 0, true);
                Log.e("bot", "bot: score" + score);
                if (maxScore < score) {
                    maxScore = score;
                    maxBoard = boardField;
                }

            }
        }
        ArrayList<BoardField> teemBoard = getArrayTeamBoard(maxBoard.getTicTacToe(),gameState);
        teemBoard.add(maxBoard);
        maxBoard.setTicTacToe(moveTicTacToe);

        ArrayList<BoardField> arrayList = moveTicTacToe == TicTacToe.cross ? crossArray : zeroArray;
        arrayList.add(maxBoard);
        if (checkFinish(moveTicTacToe, new GameState(boardFieldsArray, moveTicTacToe, crossArray, zeroArray, coordinatesByField))) {
            win(moveTicTacToe);
            moveTicTacToe = TicTacToe.empty;

        } else {
            moveTicTacToe = moveTicTacToe == TicTacToe.cross ? TicTacToe.zero : TicTacToe.cross;
        }
    }

    public boolean checkFinish(TicTacToe winTicTacToe, GameState gameState) {
        ArrayList<BoardField> arrayList = getArrayTeamBoard(winTicTacToe, gameState);
        for (BoardField b : arrayList) {
            ArrayList<VectorsFindingVictory.Vector> vectorArrayList = VectorsFindingVictory.getVector();
            Coordinates coordinatesBoard = gameState.getCoordinatesByField().get(b);
            for (VectorsFindingVictory.Vector v : vectorArrayList) {
                int score = 0;
                if (!isInBoard(new Coordinates(coordinatesBoard.getX() + v.getX(),
                        coordinatesBoard.getY() + v.getY()))) {
                    continue;
                }
                while (isInBoard(new Coordinates(coordinatesBoard.getX() + v.getX(),
                        coordinatesBoard.getY() + v.getY()))) {
                    BoardField board = gameState.getBoardFieldsArray()[coordinatesBoard.getX() + v.getX()][coordinatesBoard.getY() + v.getY()];
                    if (b.getTicTacToe() == board.getTicTacToe()) {
                        score++;
                        coordinatesBoard = new Coordinates(coordinatesBoard.getX() + v.getX(),
                                coordinatesBoard.getY() + v.getY());
                    } else {
                        break;
                    }
                }
//                do {
//                    BoardField board = gameState.getBoardFieldsArray()[coordinatesBoard.getX() + v.getX()][coordinatesBoard.getY() + v.getY()];
//                    if (b.getTicTacToe() == board.getTicTacToe()) {
//                        score++;
//                        coordinatesBoard = new Coordinates(coordinatesBoard.getX() + v.getX(),
//                                coordinatesBoard.getY() + v.getY());
//                    } else {
//                        break;
//                    }
//                } while (isInBoard(new Coordinates(coordinatesBoard.getX() + v.getX(),
//                        coordinatesBoard.getY() + v.getY())));
                if (score == 2) {
                    return true;
                }
            }
        }
        return false;
    }


    public ArrayList<BoardField> getArrayTeamBoard(TicTacToe ticTacToe, GameState gameState) {
        return ticTacToe == TicTacToe.cross ? gameState.getCrossArray() : gameState.getZeroArray();
    }

    public static boolean isInBoard(Coordinates coordinates) {
        return coordinates.getX() >= 0 &&
                coordinates.getX() < SIZE_BOARD &&
                coordinates.getY() >= 0 &&
                coordinates.getY() < SIZE_BOARD;
    }

    private void generateBoard(LinearLayout board) {
        LinearLayout columns = new LinearLayout(context);
        columns.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1
        ));
        columns.setOrientation(LinearLayout.VERTICAL);

        for (int x = 0; x < boardFieldsArray.length; x++) {
            LinearLayout row = new LinearLayout(context);
            row.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1
            ));

            for (int y = 0; y < boardFieldsArray[x].length; y++) {
                BoardField boardField = new BoardField(context);
                row.addView(boardField);
                boardFieldsArray[x][y] = boardField;
                coordinatesByField.put(boardField, new Coordinates(x, y));
            }
            columns.addView(row);
        }
        board.addView(columns);
    }
}
