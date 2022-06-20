package com.example.tictactoeapplut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Button again = findViewById(R.id.button6);
        Button home = findViewById(R.id.button5);
        TextView turn = findViewById(R.id.textView5);
        again.setVisibility(View.GONE);
        home.setVisibility(View.GONE);
        String[] names = getIntent().getStringArrayExtra("PLAYERS");
        assert names != null;
        if (names[0].equals("")){

            turn.setText((String)("Player 1's turn"));
        }else{
            turn.setText((String)(names[0] + "'s turn"));
        }
        board = findViewById(R.id.board);

        board.setUp(again, home, turn, names);
    }

    public void againClick(View view) {
        board.resetGame();
        board.invalidate();
    }

    public void homeClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}