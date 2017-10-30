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

public class AreaActivity extends AppCompatActivity {

    ListView listView;
    ActionBar Bar;
    Area_class area_class;
    Area_Adapter area_adapter;
    ArrayList<Area_class> area_list;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);

        Bar = getSupportActionBar();
        Bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C0B5F")));

        Intent intent = getIntent();
        String value_govern = intent.getStringExtra("value_govern");

        AreaBackground areaBackground = new AreaBackground();
        areaBackground.execute("http://192.168.1.9/flower_webservices/area.php" , value_govern);
        //Toast.makeText(AreaActivity.this , value_govern , Toast.LENGTH_LONG).show();

    }

    private class AreaBackground extends AsyncTask<String , String , String>{

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
            area_list  = new ArrayList<Area_class>();
            listView = (ListView) findViewById(R.id.list_area);

            for (int i=0 ; i<jsonArray.length() ; i++){
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
                    area_class = new Area_class();
                    area_class.setId(jsonObject.getInt("ID"));
                    area_class.setName(jsonObject.getString("Name"));

                    area_list.add(area_class);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            area_adapter = new Area_Adapter(AreaActivity.this , 2 , area_list);
            listView.setAdapter(area_adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                    //String area_value = String.valueOf(parent.getItemAtPosition(position)).toString();
                    String area_value = area_list.get(position).getName();
                    //Toast.makeText(AreaActivity.this , area_value , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AreaActivity.this , ChoiceActivity.class);
                    intent.putExtra("area_value" , area_value);
                    startActivity(intent);
                }
            });







        }
    }
}
