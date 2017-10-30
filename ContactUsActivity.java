package com.example.wallymisr.flowersapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ContactUsActivity extends AppCompatActivity {

    ActionBar Bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Bar = getSupportActionBar();
        Bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C0B5F")));
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
                    startActivity(new Intent(getApplicationContext() , RegisterationActivity.class));
                }

                if(ID == R.id.back){
                    finish();
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
            Intent intent = new Intent(ContactUsActivity.this , RegisterationActivity.class);
            startActivity(intent);

        }

        if(id == R.id.contactus){
            Intent intent = new Intent(ContactUsActivity.this , ContactUsActivity.class);
            startActivity(intent);

        }

        return true;

    }

}
