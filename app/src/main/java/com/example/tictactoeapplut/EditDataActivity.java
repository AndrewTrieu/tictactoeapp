package com.example.tictactoeapplut;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {

    private Button btnDelete;
    private TextView txtPlayers, txtWinner, txtTime;

    DatabaseHelper mDatabaseHelper;

    private String selectedPlayers, selectedWinner, selectedTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        txtPlayers = (TextView) findViewById(R.id.txtPlayers);
        txtWinner = (TextView) findViewById(R.id.txtWinner);
        txtTime = (TextView) findViewById(R.id.txtTime);
        mDatabaseHelper = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        selectedPlayers = receivedIntent.getStringExtra("players");
        selectedWinner = receivedIntent.getStringExtra("winner");
        selectedTime = receivedIntent.getStringExtra("time");

        txtPlayers.setText(selectedPlayers);
        txtWinner.setText(selectedWinner);
        txtTime.setText(selectedTime);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteMatch(selectedTime);
                txtPlayers.setText("");
                txtWinner.setText("");
                txtTime.setText("");
                toaster();
            }
        });

    }
    private void toaster(){
        Toast.makeText(this, "Match removed", Toast.LENGTH_SHORT).show();
    }
}
