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
 * Created by WallyMisr on 16/10/2017.
 */
public class Area_Adapter extends ArrayAdapter {

    Context context;
    int Resource;
    List<Area_class> area_list;

    public Area_Adapter(Context context, int resource, List<Area_class> objects) {
        super(context, resource, objects);
        this.context = context;
        this.Resource = resource;
        this.area_list = objects;
    }

    public View getView(final int position , View convertView, ViewGroup parent){
        final LayoutInflater Inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = Inflater.inflate(R.layout.area_list, parent, false);
        TextView area_text = (TextView) view.findViewById(R.id.area_txt);

        area_text.setText(area_list.get(position).getName());

        return view;

    }

}
