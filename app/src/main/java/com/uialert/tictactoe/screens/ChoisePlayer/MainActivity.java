package com.uialert.tictactoe.screens.ChoisePlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.uialert.tictactoe.screens.play.PlayActivity;
import com.uialert.tictactoe.R;
import com.uialert.tictactoe.engine.Engine;
import com.uialert.tictactoe.engine.enumGame.Players;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onePlayers(View view) {
        Engine.players = Players.One;
        startActivity(new Intent(this, PlayActivity.class));
    }

    public void twoPlayers(View view) {
        Engine.players = Players.Two;
        startActivity(new Intent(this,PlayActivity.class));
    }
}