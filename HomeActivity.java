package com.example.wallymisr.flowersapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    ActionBar Bar;
    private ImageView image1;
    private int[] imageArray;
    private int startIndex,endIndex;
    int currentIndex;
    Drawable img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bar = getSupportActionBar();
        Bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C0B5F")));

        Intent intent2 = getIntent();
        String name = intent2.getStringExtra("name");
        String Id =  intent2.getStringExtra("id");
        Toast.makeText(HomeActivity.this , "Welcome" +" " + name , Toast.LENGTH_LONG).show();

        image1 = (ImageView)findViewById(R.id.homeimg);
        imageArray = new int[4];
        imageArray[0] = R.drawable.shop;
        imageArray[1] = R.drawable.shop2;
        imageArray[2] = R.drawable.shop3;
        imageArray[3] = R.drawable.shop4;

        startIndex = 0;
        endIndex = 3;
        nextImage();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        ActionMenuView bottomBar = (ActionMenuView) findViewById(R.id.bottom_toolbar);
        Menu bottomMenu =  bottomBar.getMenu();
        getMenuInflater().inflate(R.menu.bottom_menu, bottomMenu);

        bottomBar.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int ID = item.getItemId();

                if(ID == R.id.home){
                    startActivity(new Intent(getApplicationContext() , HomeActivity.class));
                }

                if(ID == R.id.back){
                    finish();
                    //startActivity(new Intent(getApplicationContext() , ));
                }

                if (ID == R.id.mobile){
                    Intent intent = new Intent(getApplicationContext() , CountryActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        //return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.flowers){
            Intent intent = new Intent(HomeActivity.this , RegisterationActivity.class);
            startActivity(intent);

        }

        if(id == R.id.contactus){
            Intent intent = new Intent(HomeActivity.this , ContactUsActivity.class);
            startActivity(intent);

        }

        return true;

    }

    public void nextImage(){
        // image1.setImageResource(imageArray[currentIndex]);
        img = getResources().getDrawable(imageArray[currentIndex]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            image1.setBackground(img);
        }
        Animation rotateimage = AnimationUtils.loadAnimation(this, R.animator.scalanimation);
        image1.startAnimation(rotateimage);
        currentIndex++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentIndex>endIndex){
                    currentIndex--;
                    previousImage();
                }else{
                    nextImage();
                }

            }
        },2000); // here 1000(1 second) interval to change from current  to next image

    }

    public void previousImage(){
        //image1.setImageResource(imageArray[currentIndex]);
        img = getResources().getDrawable(imageArray[currentIndex]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            image1.setBackground(img);
        }
        Animation rotateimage = AnimationUtils.loadAnimation(this, R.animator.scalanimation);
        image1.startAnimation(rotateimage);
        currentIndex--;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(currentIndex<startIndex){
                    currentIndex++;
                    nextImage();
                }else{
                    previousImage(); // here 1000(1 second) interval to change from current  to previous image
                }
            }
        },2000);

    }



}
