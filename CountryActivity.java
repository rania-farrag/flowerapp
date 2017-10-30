package com.example.wallymisr.flowersapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends AppCompatActivity {

    country_class country_class;
    ArrayList<country_class> country;

    Country_Adapter country_adapter;

    String content;
    ActionBar actionBar;
    ListView country_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C0B5F")));

        CountryBackground countryBackground = new CountryBackground();
        countryBackground.execute("http://192.168.1.9/flower_webservices/Country.php");


    }

    private class CountryBackground extends AsyncTask<String , String , String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            content =DBconnection.getdata(params);

            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JSONArray jsonArray = JsonParser.parser(s);
            country  = new ArrayList<country_class>();


            for (int i = 0 ; i<jsonArray.length() ; i++){
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));

                    country_class = new country_class();

                    country_class.setId(jsonObject.getInt("ID"));
                    country_class.setName(jsonObject.getString("Name"));

                    country.add(country_class);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            country_listView = (ListView) findViewById(R.id.count_list);

            country_adapter = new Country_Adapter(CountryActivity.this , 2 , country);
            country_listView.setAdapter(country_adapter);

            country_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String value_gov = (String) listView.getItemAtPosition(position); m4 sha8leen
                    //String value_gov = String.valueOf(parent.getItemAtPosition(position)).toString();

                    String value_country = country.get(position).getName();
                    //Toast.makeText(CountryActivity.this , value_country , Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(CountryActivity.this , GovernorateActivity.class);
                    intent.putExtra("value_country" , value_country);
                    startActivity(intent);
                }
            });

        }
    }
}
