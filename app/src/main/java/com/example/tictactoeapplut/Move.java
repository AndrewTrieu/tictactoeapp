package com.example.tictactoeapplut;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Move {
    private int[][] board;
    private String[] names = {"Player 1", "Player 2"};
    private int[] winType = {-1, -1, -1};
    private Button again;
    private Button home;
    private TextView turn;
    private int player = 1;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");

    DatabaseHelper databaseHelper;

    Move(Context context) {
        databaseHelper = new DatabaseHelper(context);
        board = new int[3][3];
        for (int r=0; r<3; r++) {
            for (int c=0; c<3; c++) {
                board[r][c] = 0;
            }
        }
    }

    public boolean updateBoard(int row, int column) {
        if (board[row-1][column-1]==0) {
            board[row-1][column-1] = player;
            if (player == 1) {
                turn.setText((String)(names[1]+"'s turn"));
            } else {
                turn.setText((String)(names[0]+"'s turn"));
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean winCheck() {
        boolean won = false;

        for (int r = 0; r < 3; r++) {
            if (board[r][0] == board[r][1] && board[r][0] == board[r][2] && board[r][0] != 0) {
                winType = new int[] {r, 0, 1};
                won = true;
                break;
            }
        }
        for (int c = 0; c < 3; c++) {
            if (board[0][c] == board[1][c] && board[0][c] == board[2][c] && board[0][c] != 0) {
                winType = new int[] {0, c, 2};
                won = true;
                break;
            }
        }
        if (board[0][0]==board[1][1] && board[0][0]==board[2][2] && board[0][0]!=0) {
            winType = new int[] {0, 2, 3};
            won = true;
        }
        if (board[2][0]==board[1][1] && board[2][0]==board[0][2] && board[2][0]!=0) {
            winType = new int[] {2, 2, 4};
            won = true;
        }

        int boardFilled = 0;
        for (int r=0; r<3; r++) {
            for (int c=0; c<3; c++) {
                if (board[r][c]!=0){
                    boardFilled+=1;
                }
            }
        }

        if (won) {
            again.setVisibility(View.VISIBLE);
            home.setVisibility(View.VISIBLE);
            turn.setText((String)(names[player-1]+" won!"));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            databaseHelper.addData(names[0]+" vs "+names[1], names[player-1], sdf.format(timestamp));
            return true;
        } else if(boardFilled==9){
            again.setVisibility(View.VISIBLE);
            home.setVisibility(View.VISIBLE);
            turn.setText((String)("Tie game!"));
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            databaseHelper.addData(names[0]+" vs "+names[1], "Tie", sdf.format(timestamp));
            return false;
        } else {
            return false;
        }
    }

    public void resetBoard() {
        for (int r=0; r<3; r++) {
            for (int c=0; c<3; c++) {
                board[r][c] = 0;
            }
        }
        player = 1;
        again.setVisibility(View.GONE);
        home.setVisibility(View.GONE);
        turn.setText((String)(names[0]+"'s turn"));
    }

    public void setAgain(Button again) {
        this.again = again;
    }

    public void setHome(Button home) {
        this.home = home;
    }

    public void setTurn(TextView turn) {
        this.turn = turn;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

    public int[] getWinType() {
        return winType;
    }

}
