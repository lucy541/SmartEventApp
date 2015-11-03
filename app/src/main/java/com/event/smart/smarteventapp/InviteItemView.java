package com.event.smart.smarteventapp;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by lucy on 10/29/15.
 */


@EViewGroup(R.layout.invite_item)
public class InviteItemView  extends LinearLayout{

    @ViewById(R.id.textView11)
    TextView txt1;
    @ViewById(R.id.textView12)
    TextView txt2;

    public InviteItemView(Context context) {
        super(context);
    }

    public void Bind (Guest guests){
        txt1.setText(guests.getName());
        txt2.setText(guests.getCode());


    }


}
