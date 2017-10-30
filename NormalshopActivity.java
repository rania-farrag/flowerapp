package com.example.wallymisr.flowersapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class NormalshopActivity extends AppCompatActivity {

    ActionBar Bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normalshop);

        Bar = getSupportActionBar();
        Bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C0B5F")));

        Intent intent2 = getIntent();
        String area_value = intent2.getStringExtra("area_value");
        String shop_type = intent2.getStringExtra("shop_type");
        Toast.makeText(NormalshopActivity.this , area_value , Toast.LENGTH_LONG).show();
        Toast.makeText(NormalshopActivity.this , shop_type , Toast.LENGTH_LONG).show();

    }
}
