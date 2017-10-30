package com.example.wallymisr.flowersapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WeddingshopActivity extends AppCompatActivity {

    ActionBar Bar;

    Shop_Adapter shop_adapter;
    shop_class shopClass;
    ListView shoplistView;
    ArrayList<shop_class> shoplist;
    String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weddingshop);

        //String[] arr = {"HOME" , "Setting" , "jhfjsdhjsdh","jsdjffk","hfsdjkhfjsd","hfjsdhfj"};
        //Integer[] pics = {R.drawable.home , R.drawable.arrow};

        Bar = getSupportActionBar();
        Bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C0B5F")));

        //ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.weddinglist, R.id.weddingtxt, arr);
        //ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.weddinglist, R.id.weddingtxt,arr);

        //listView.setAdapter(arrayAdapter);

        Intent intent2 = getIntent();
        String area_value = intent2.getStringExtra("area_value");
        String shop_type = intent2.getStringExtra("shop_type");
        //Toast.makeText(WeddingshopActivity.this , area_value , Toast.LENGTH_LONG).show();
        //Toast.makeText(WeddingshopActivity.this , shop_type , Toast.LENGTH_LONG).show();
        WeddingBackground weddingBackground = new WeddingBackground();
        weddingBackground.execute("http://192.168.1.9/flower_webservices/weddingshops.php" , area_value , shop_type);

    }

    public class WeddingBackground extends AsyncTask<String , String , String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            content = DBconnection.getdata(params);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray jsonArray = JsonParser.parser(s);
            shopClass = new shop_class();
            shoplist = new ArrayList<shop_class>();

            for (int i=0 ; i<jsonArray.length() ; i++){
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
                    shopClass.setName(jsonObject.getString("name"));
                    shopClass.setAddress(jsonObject.getString("address"));
                    shopClass.setPhone(jsonObject.getString("phone"));
                    shopClass.setImage(jsonObject.getString("pic"));

                    shoplist.add(shopClass);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            shoplistView = (ListView) findViewById(R.id.weddinglistview);

            shop_adapter = new Shop_Adapter(WeddingshopActivity.this , 2 , shoplist);
            shoplistView.setAdapter(shop_adapter);

        }
    }
}
