package com.example.wallymisr.flowersapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by WallyMisr on 14/10/2017.
 */
public class Country_Adapter extends ArrayAdapter {
    Context context;
    int Resources;
    List<country_class> country_list;


    public Country_Adapter(Context context, int resource, List<country_class> objects) {
        super(context, resource, objects);
        this.context = context;
        this.Resources = resource;
        this.country_list = objects;

    }

    public View getView(final int position , View convertView, ViewGroup parent){
        final LayoutInflater Inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = Inflater.inflate(R.layout.country_list, parent, false);
        TextView country_txt = (TextView) view.findViewById(R.id.country_txt);

        country_txt.setText(country_list.get(position).getName());

        return view;

    }

}
