package com.uialert.tictactoe.engine;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uialert.tictactoe.R;
import com.uialert.tictactoe.engine.enumGame.TicTacToe;

public class BoardField extends RelativeLayout {
    private static int instances = 0;
    public int instancesId = 0;
    TextView textView;

    public TicTacToe getTicTacToe() {
        return ticTacToe;
    }

    private TicTacToe ticTacToe = TicTacToe.empty;

    public BoardField(Context context) {
        super(context);

        this.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1
        ));
        this.setGravity(Gravity.CENTER);
        setBackgroundColor(getResources().getColor(R.color.purple_500));


        instancesId = instances;
        instances++;
        Log.e("fsdfs", "BoardField: " + instancesId);

        textView = new TextView(context);
        textView.setTextSize(40);
        textView.setTextColor(getResources().getColor(R.color.teal_200));
        //textView.setText(instancesId + "");
        textView.setText("▓");

        addView(textView);
    }

    void setTicTacToe(TicTacToe ticTacToe) {
        this.ticTacToe = ticTacToe;
        switch (ticTacToe) {
            case zero:
                textView.setText("0");
                break;
            case cross:
                textView.setText("X");
                break;
            case empty:
                textView.setText("▓");
                break;
        }
    }

    @Override
    public String toString() {
        return "BoardField{" +
                "instancesId=" + instancesId +
                ", textView=" + textView.getText().toString() +
                ", ticTacToe=" + ticTacToe +
                '}';
    }

    public char getChar(){
        char returnChar = '▓';
        if (ticTacToe == TicTacToe.cross){returnChar = 'X';}
        if (ticTacToe == TicTacToe.zero){returnChar = '0';}
        return returnChar;
    }
}
