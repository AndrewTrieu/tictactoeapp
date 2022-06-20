package com.example.tictactoeapplut;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView description = (TextView) findViewById(R.id.textView2);
        TextView link = (TextView) findViewById(R.id.textView3);
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri lut = Uri.parse((String)"https://www.lut.fi/en");
                Intent toLUT = new Intent(Intent.ACTION_VIEW, lut);
                if (toLUT.resolveActivity(getPackageManager()) != null) {
                    startActivity(toLUT);
                }
            }
        });
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri github = Uri.parse((String)"https://github.com/AndrewTrieu/tictactoeapp");
                Intent toGitHub = new Intent(Intent.ACTION_VIEW, github);
                if (toGitHub.resolveActivity(getPackageManager()) != null) {
                    startActivity(toGitHub);
                }
            }
        });
    }

    public void startClick(View view){
        Intent intent = new Intent(this, Prepare.class);
        startActivity(intent);
    }
    public void recordClick(View view){
        Intent intent = new Intent(this, ListDataActivity.class);
        startActivity(intent);
    }
}