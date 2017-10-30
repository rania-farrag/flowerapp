package com.example.wallymisr.flowersapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WallyMisr on 11/10/2017.
 */
public class JsonParser {

    public static JSONArray parser(String data){

        JSONArray array = null;

        try {
            JSONObject jsonObject = new JSONObject(data);

            if (jsonObject.getBoolean("Success")){
                if (jsonObject.has("item")){
                    array = new JSONArray(String.valueOf(jsonObject.getJSONArray("item")));

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }
}
