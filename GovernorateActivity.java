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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GovernorateActivity extends AppCompatActivity {
    Governorate_Adapter governorate_adapter;
    String content;
    ArrayList<Governorate_class> governorate_list;
    Governorate_class governorate_class;
    ActionBar Bar;
    ListView gov_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_governorate);

        Bar = getSupportActionBar();
        Bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C0B5F")));

        Intent intent = getIntent();
        String country_val = intent.getStringExtra("value_country");
        //Toast.makeText(GovernorateActivity.this , country_val , Toast.LENGTH_LONG).show();

        GovernBackground governBackground = new GovernBackground();
        governBackground.execute("http://192.168.1.9/flower_webservices/governorates.php" , country_val);
    }


    private class GovernBackground extends AsyncTask<String , String , String> {

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
            governorate_list  = new ArrayList<Governorate_class>();


            for (int i = 0 ; i<jsonArray.length() ; i++){
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));

                    governorate_class = new Governorate_class();

                    governorate_class.setId(jsonObject.getInt("ID"));
                    governorate_class.setName(jsonObject.getString("Name"));

                    governorate_list.add(governorate_class);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            gov_listview = (ListView) findViewById(R.id.gov_list);

            governorate_adapter = new Governorate_Adapter(GovernorateActivity.this , 2 , governorate_list);
            gov_listview.setAdapter(governorate_adapter);

            gov_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //String value_gov = (String) listView.getItemAtPosition(position); m4 sha8leen
                    //String value_gov = String.valueOf(parent.getItemAtPosition(position)).toString();


                    String value_govern = governorate_list.get(position).getName();
                    //Toast.makeText(GovernorateActivity.this , value_govern , Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(GovernorateActivity.this , AreaActivity.class);
                    intent.putExtra("value_govern" , value_govern);
                    startActivity(intent);
                }
            });

        }
    }
}
