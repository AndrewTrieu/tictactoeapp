package com.example.tictactoeapplut;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listPlayers = new ArrayList<>();
        ArrayList<String> listWinner = new ArrayList<>();
        ArrayList<String> listTime = new ArrayList<>();
        while(data.moveToNext()){

            listPlayers.add(data.getString(1));
            listWinner.add(data.getString(2));
            listTime.add(data.getString(3));
        }
        CustomAdapter adapter = new CustomAdapter(this, listPlayers, listWinner, listTime);
        mListView.setAdapter(adapter);
    }
}
