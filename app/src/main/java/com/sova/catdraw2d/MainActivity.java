package com.sova.catdraw2d;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Draw2d draw2D = new Draw2d(this);
        setContentView(draw2D);
    }
}