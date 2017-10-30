package com.example.wallymisr.flowersapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterationActivity extends AppCompatActivity {

    ActionBar Bar;
    User user;
    String content = null;
    Button register;
    EditText username, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        register = (Button) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);

        Bar = getSupportActionBar();
        Bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4C0B5F")));

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = username.getText().toString();
                String mail = email.getText().toString();

                if (name.equals("") || mail.equals("")){
                    Toast.makeText(RegisterationActivity.this, "Please! Fill your data", Toast.LENGTH_LONG).show();
                }

                else {
                    //Intent intent = new Intent(RegisterationActivity.this , HomeActivity.class);
                    //startActivity(intent);
                    RegisterBackground registerBackground = new RegisterBackground();
                    registerBackground.execute("http://192.168.1.9/flower_webservices/index.php" , name , mail);
                }

            }
        });

    }

    private class RegisterBackground extends AsyncTask<String , String , String>{

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

            //String name;

            JSONArray jsonArray = JsonParser.parser(s);
            for (int i= 0 ; i<jsonArray.length() ; i++){
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.getJSONObject(i)));
                    user = new User();

                    user.setId(jsonObject.getInt("ID"));
                    user.setName(jsonObject.getString("username"));
                    user.setMail(jsonObject.getString("mail"));

                    String name = jsonObject.getString("username");
                    String id = jsonObject.getString("ID");

                    Intent intent = new Intent(RegisterationActivity.this , HomeActivity.class);
                    intent.putExtra("name" , name);
                    intent.putExtra("id" , id);
                    startActivity(intent);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //Intent intent = new Intent(RegisterationActivity.this , HomeActivity.class);
            //startActivity(intent);

            super.onPostExecute(s);
        }
    }
}
