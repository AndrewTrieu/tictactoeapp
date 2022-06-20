package com.example.tictactoeapplut;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Board extends View {

    private final int boardColor;
    private final int XColor;
    private final int OColor;
    private final int winColor;
    private int cellSize = getWidth()/3;
    private final Paint paint = new Paint();
    private final Move game;
    private boolean winLine = false;

    public Board(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game = new Move(context);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Board,0,0);

        try {
            boardColor = a.getInteger(R.styleable.Board_boardColor, 0);
            XColor = a.getInteger(R.styleable.Board_XColor, 0);
            OColor = a.getInteger(R.styleable.Board_OColor, 0);
            winColor = a.getInteger(R.styleable.Board_winColor, 0);

        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);

        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellSize = size/3;

        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawBoard(canvas);
        drawMove(canvas);

        if (winLine) {
            paint.setColor(winColor);
            drawWinLine(canvas);

        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            int row = (int) Math.ceil(y/cellSize);
            int column = (int) Math.ceil(x/cellSize);
            if (!winLine) {
                if (game.updateBoard(row, column)) {
                    invalidate();
                    if (game.winCheck()) {
                        winLine = true;
                        invalidate();
                    }
                    if (game.getPlayer() % 2 == 0) {
                        game.setPlayer(game.getPlayer() - 1);
                    } else {
                        game.setPlayer(game.getPlayer() + 1);
                    }
                }
            }

            invalidate();

            return true;
        }

        return false;
    }

    private void drawBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
        for (int x=1; x<3; x++) {
            canvas.drawLine(cellSize*x, 0, cellSize*x, canvas.getWidth(), paint);
        }

        for (int y=1; y<3; y++) {
            canvas.drawLine(0, cellSize*y, canvas.getWidth(), cellSize*y, paint);
        }
        paint.setStrokeWidth(32);
        canvas.drawLine(0,0,cellSize*3,0,paint);
        paint.setStrokeWidth(32);
        canvas.drawLine(0,cellSize*3,cellSize*3,cellSize*3,paint);
        paint.setStrokeWidth(16);
        canvas.drawLine(0,0,0,cellSize*3,paint);
        paint.setStrokeWidth(16);
        canvas.drawLine(cellSize*3,0,cellSize*3,cellSize*3,paint);
    }

    private void drawMove(Canvas canvas) {
        for (int r=0; r<3; r++) {
            for (int c=0; c<3; c++) {
                if (game.getBoard()[r][c]!=0) {
                    if (game.getBoard()[r][c]==1) {
                        drawX(canvas, r, c);
                    } else {
                        drawO(canvas, r, c);
                    }
                }
            }
        }
    }

    private void drawX(Canvas canvas, int row, int column) {
        paint.setColor(XColor);
        canvas.drawLine((float)((column+1)*cellSize - cellSize*0.1), (float)(row*cellSize+cellSize*0.1), (float)(column*cellSize+cellSize*0.1), (float)((row+1)*cellSize-cellSize*0.1), paint);
        canvas.drawLine((float)(column*cellSize+cellSize*0.1), (float)(row*cellSize+cellSize*0.1), (float)((column+1)*cellSize-cellSize*0.1), (float)((row+1)*cellSize-cellSize*0.1), paint);

    }

    private void drawO(Canvas canvas, int row, int column) {
        paint.setColor(OColor);
        canvas.drawOval((float)(column*cellSize+cellSize*0.1), (float)(row*cellSize+cellSize*0.1), (float)(column*cellSize+cellSize-cellSize*0.1), (float)(row*cellSize+cellSize-cellSize*0.1), paint);
    }

    private void drawHorizontal(Canvas canvas, int row, int column) {
        canvas.drawLine(column, row*cellSize + cellSize/2, cellSize*3, row*cellSize+cellSize/2,paint);
    }

    private void drawVertical(Canvas canvas, int row, int column) {
        canvas.drawLine(column*cellSize+cellSize/2, row, column*cellSize+cellSize/2, cellSize*3,paint);
    }

    private void drawSlopeUp(Canvas canvas) {
        canvas.drawLine(0, cellSize*3, cellSize*3, 0 ,paint);
    }

    private void drawSlopeDown(Canvas canvas) {
        canvas.drawLine(0, 0, cellSize*3, cellSize*3 ,paint);
    }

    private void drawWinLine(Canvas canvas) {
        int row = game.getWinType()[0];
        int column = game.getWinType()[1];

        switch (game.getWinType()[2]) {
            case 1:
                drawHorizontal(canvas, row, column);
                break;
            case 2:
                drawVertical(canvas, row, column);
                break;
            case 3:
                drawSlopeDown(canvas);
                break;
            case 4:
                drawSlopeUp(canvas);
                break;
        }
    }

    public void setUp(Button again, Button home, TextView display, String[] names) {
        game.setAgain(again);
        game.setHome(home);
        game.setTurn(display);
        if (!names[0].equals("") && !names[1].equals("")){
            game.setNames(names);
        }
    }

    public void resetGame() {
        game.resetBoard();
        winLine = false;
    }
}
