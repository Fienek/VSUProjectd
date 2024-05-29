package com.example.task3test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.task3test.Game.MySurfaceView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MySurfaceView mySurfaceView = new MySurfaceView(this);
        setContentView(mySurfaceView);
    }
}