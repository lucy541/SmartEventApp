package com.event.smart.smarteventapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

/**
 * Created by lucy on 9/21/15.
 */

public class ListAdapter  extends BaseAdapter{


    Context context;

    ArrayList<Guest> guests;


    public ListAdapter(Context context, ArrayList<Guest> guests) {

        this.guests = guests;
        this.context = context;
    }

    @Override
    public int getCount() {

        return guests.size();
    }

    @Override
    public Object getItem(int i) {
        return guests.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        InviteItemView  inviteItemView = null;

        if(view == null){

            inviteItemView  = new InviteItemView(context);
        }else{
            inviteItemView  =(InviteItemView) view;
         }


            inviteItemView.Bind(guests.get(i));

       /* LayoutInflater inflater = LayoutInflater.from(context);*/

      /* View v = (TextView)inflater.inflate(android.R.layout.simple_list_item_1, null, true);
        TextView txt1 = (TextView) v.findViewById(android.R.id.text1);
        txt1.setText("Lucy Gomez");*/



        return inviteItemView;
    }
}
