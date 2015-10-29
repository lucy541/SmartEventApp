package com.event.smart.smarteventapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.ViewById;

@NoTitle
@Fullscreen
@EActivity(R.layout.info_activity)
public class DetailActivity extends Activity {

    @ViewById(R.id.textView2)
    TextView nombre;
    @ViewById(R.id.textView3)
    TextView personas;
    @ViewById(R.id.textView4)
    TextView actuales;
    @ViewById(R.id.textView5)
    TextView ninos;
    @ViewById(R.id.textView6)
    TextView mesa;

    String id;




    @AfterViews
    void UpdateViewInfo(){


        nombre.setText(this.getIntent().getStringExtra("NOMBRE"));
        personas.setText(this.getIntent().getStringExtra("PERSONAS"));
        actuales.setText(this.getIntent().getStringExtra("ACTUALES"));
        ninos.setText(this.getIntent().getStringExtra("NINOS"));
        mesa.setText(this.getIntent().getStringExtra("MESA"));
        id = this.getIntent().getStringExtra("ID");


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
