package com.example.wallymisr.flowersapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChoiceActivity extends AppCompatActivity {

    ActionBar Bar;
    CircleImageView wedding , normal;
    String shop_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        Intent intent = getIntent();
        final String area_value = intent.getStringExtra("area_value");
        //Toast.makeText(ChoiceActivity.this , area_value , Toast.LENGTH_LONG).show();

        Bar = getSupportActionBar();
        Bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C0B5F")));

        wedding = (CircleImageView) findViewById(R.id.mariaag);
        normal = (CircleImageView) findViewById(R.id.normal);


        Animation animation = AnimationUtils.loadAnimation(getApplicationContext() , R.animator.rotateanimation);
        wedding.setAnimation(animation);
        normal.setAnimation(animation);

        wedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shop_type = "wedding";
                Intent intent2 = new Intent(ChoiceActivity.this , WeddingshopActivity.class);
                intent2.putExtra("area_value" , area_value);
                intent2.putExtra("shop_type" , shop_type);
                startActivity(intent2);
            }
        });

        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shop_type = "normal";
                Intent intent2 = new Intent(ChoiceActivity.this , NormalshopActivity.class);
                intent2.putExtra("area_value" , area_value);
                intent2.putExtra("shop_type" , shop_type);
                startActivity(intent2);
            }
        });







    }
}
