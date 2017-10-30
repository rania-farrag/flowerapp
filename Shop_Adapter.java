package com.example.wallymisr.flowersapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WallyMisr on 18/10/2017.
 */
public class Shop_Adapter extends ArrayAdapter {

    ImageView image;
    TextView wedding_text;

    Context context;
    //AsyncTask asyncTask;
    //Bitmap bitmapimg;
    int Resource;
    //List<shop_class> listshop;

    ArrayList<shop_class> listshop;

    public Shop_Adapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        this.context = context;
        this.Resource = resource;
        this.listshop = objects;

    }

    public View getView(final int position , View convertView, ViewGroup parent){
        final LayoutInflater Inflater = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = Inflater.inflate(R.layout.weddinglist, parent, false);
            image = (ImageView) view.findViewById(R.id.weddingimg);
            wedding_text = (TextView) view.findViewById(R.id.weddingtxt);
            //wedding_text.setText(listshop.get(position).getName());
            String ImageUrl = (listshop.get(position).getImage());


        //Picasso.with(WeddingshopActivity.this).load(ImageUrl.parse(card.getField("profile_picture_url"))).into(viewHolder.tvPersence);

            Picasso.with(context)
                    .load(ImageUrl)
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded (final Bitmap bitmap, Picasso.LoadedFrom from) {
                  /*Save the bitmap or do something with it here */
                            //Set it in the ImageView

                            wedding_text.setText(listshop.get(position).getName());
                            image.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {}

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {}
                    });

        return view;




        //WeddingshopActivity weddingshopActivity = new WeddingshopActivity();
        //asyncTask = new AsyncTask();
        //bitmapimg = asyncTask.getBitmapfromurl(imageurl);
        //bitmapimg = weddingshopActivity.getBitmapfromurl(imageurl);
        //Picasso.with(context).load(imageurl).into(image);

        /*Picasso
                .with(context)
                .load(imageurl)
                .fit() // will explain later
                .into((ImageView) convertView);*/

        //image.setImageBitmap(bitmapimg);


        /*URL url = null;
        try {
            url = new URL(imageurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap mybitmap = BitmapFactory.decodeStream(inputStream);
            image.setImageBitmap(mybitmap);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        //bitmapimg = weddingshopActivity.getBitmapfromurl(imageurl);
        //AsyncTask asyncTask = new AsyncTask();

        //bitmapimg = asyncTask.getBitmapfromurl(imageurl);
        //image.setImageBitmap(bitmapimg);

        /*Drawable imagee = new BitmapDrawable(getContext().getResources(),);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            image.setImageBitmap(my);
        }*/
        //return view;

    }

}
