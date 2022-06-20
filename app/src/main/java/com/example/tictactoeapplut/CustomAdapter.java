package com.example.tictactoeapplut;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private static final String TAG = "CustomAdapter";
    Context context;
    ArrayList<String> name;
    ArrayList<String> winner;
    ArrayList<String> time;

    public CustomAdapter(Context context, ArrayList<String> name, ArrayList<String> winner, ArrayList<String> time) {
        this.context = context;
        this.name = name;
        this.winner = winner;
        this.time = time;
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_item,parent,false);
        TextView txtName, txtWin, txtTime;

        txtName = convertView.findViewById(R.id.txt_name);
        txtWin = convertView.findViewById(R.id.txt_win);
        txtTime = convertView.findViewById(R.id.txt_time);
        txtName.setText(name.get(position));
        txtWin.setText(winner.get(position));
        txtTime.setText(time.get(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editScreenIntent = new Intent(context, EditDataActivity.class);
                editScreenIntent.putExtra("time",txtTime.getText().toString());
                editScreenIntent.putExtra("players", txtName.getText().toString());
                editScreenIntent.putExtra("winner", txtWin.getText().toString());
                context.startActivity(editScreenIntent);
            }
        });
        return convertView;
    }
}
