package com.event.smart.smarteventapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucy on 9/21/15.
 */
public class ListAdapter  extends BaseAdapter{
   Context context;
    ArrayList<String> list;

    public ListAdapter(Context context,    ArrayList<String> list ) {
        this.context = context;
        this.list= list;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);

       View v = (TextView)inflater.inflate(android.R.layout.simple_list_item_1, null, true);
        TextView txt1 = (TextView) v.findViewById(android.R.id.text1);
        txt1.setText("Lucy Gomez");

        

        return v;
    }
}
