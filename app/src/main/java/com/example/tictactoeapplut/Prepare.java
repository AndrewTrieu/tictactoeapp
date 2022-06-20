package com.example.tictactoeapplut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Prepare extends AppCompatActivity {
    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);
        player1 = findViewById(R.id.editTextTextPersonName4);
        player2 = findViewById(R.id.editTextTextPersonName3);
    }
    public void playClick(View view) {
        String player1Name = player1.getText().toString();
        String player2Name = player2.getText().toString();

        Intent intent = new Intent(this, Game.class);
        intent.putExtra("PLAYERS", new String[] {player1Name, player2Name});
        startActivity(intent);
    }
}