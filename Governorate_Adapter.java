package com.example.wallymisr.flowersapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by WallyMisr on 14/10/2017.
 */
public class Governorate_Adapter extends ArrayAdapter {
    Context context;
    int resource;
    List<Governorate_class> gov_list;

    public Governorate_Adapter(Context context, int resource, List<Governorate_class> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.gov_list = objects;
    }

    public View getView(final int position , View convertView, ViewGroup parent){
        final LayoutInflater Inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = Inflater.inflate(R.layout.governorate_list, parent, false);
        TextView gov_text = (TextView) view.findViewById(R.id.gov_txt);

        gov_text.setText(gov_list.get(position).getName());

        return view;

    }

}
