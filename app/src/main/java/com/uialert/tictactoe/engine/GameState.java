package com.uialert.tictactoe.engine;

import android.content.Context;
import android.util.Log;

import com.uialert.tictactoe.engine.BoardField;
import com.uialert.tictactoe.engine.Coordinates;
import com.uialert.tictactoe.engine.enumGame.TicTacToe;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;

public class GameState  {

    private BoardField[][] boardFieldsArray;
    private TicTacToe moveTicTacToe;
    private ArrayList<BoardField> crossArray;
    private ArrayList<BoardField> zeroArray;
    private Map<BoardField, Coordinates> coordinatesByField;


    public GameState(BoardField[][] boardFieldsArray, TicTacToe moveTicTacToe, ArrayList<BoardField> crossArray, ArrayList<BoardField> zeroArray, Map<BoardField, Coordinates> coordinatesByField) {
        this.boardFieldsArray = boardFieldsArray;
        this.moveTicTacToe = moveTicTacToe;
        this.crossArray = crossArray;
        this.zeroArray = zeroArray;
        this.coordinatesByField = coordinatesByField;
    }

    public BoardField[][] getBoardFieldsArray() {
        return boardFieldsArray;
    }

    public void setBoardFieldsArray(BoardField[][] boardFieldsArray) {
        this.boardFieldsArray = boardFieldsArray;
    }

    public TicTacToe getMoveTicTacToe() {
        return moveTicTacToe;
    }

    public void setMoveTicTacToe(TicTacToe moveTicTacToe) {
        this.moveTicTacToe = moveTicTacToe;
    }

    public ArrayList<BoardField> getCrossArray() {
        return crossArray;
    }

    public void setCrossArray(ArrayList<BoardField> crossArray) {
        this.crossArray = crossArray;
    }

    public ArrayList<BoardField> getZeroArray() {
        return zeroArray;
    }

    public void setZeroArray(ArrayList<BoardField> zeroArray) {
        this.zeroArray = zeroArray;
    }

    public Map<BoardField, Coordinates> getCoordinatesByField() {
        return coordinatesByField;
    }

    public void setCoordinatesByField(Map<BoardField, Coordinates> coordinatesByField) {
        this.coordinatesByField = coordinatesByField;
    }




}
