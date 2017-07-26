package com.iranplanner.tourism.iranplanner.ui.fragment.home;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iranplanner.tourism.iranplanner.R;

import entity.Data;


public class NavigationItemsAdapter extends ArrayAdapter<Data> {

    Context mContext;
    int layoutResourceId;
    Data data[] = null;


    public NavigationItemsAdapter(Context mContext, int layoutResourceId, Data[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;

    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.listRowImage);
        TextView textViewName = (TextView) listItem.findViewById(R.id.listRowText);

//        YEKAN = (Typeface.createFromAsset(mContext.getAssets(),"Yekan.ttf"));

//        textViewName.setTypeface(YEKAN);

        Data folder = data[position];

        imageViewIcon.setImageResource(folder.icon);
        textViewName.setText(folder.name);

        return listItem;
    }
}