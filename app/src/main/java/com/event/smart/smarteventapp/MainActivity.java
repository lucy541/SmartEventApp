package com.event.smart.smarteventapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;


import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.HttpsClient;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @ViewById(R.id.button2)
    Button  scan;
    @ViewById(R.id.button3)
    Button buttonSearch;
    @ViewById(R.id.listView)
    ListView lista;
    @HttpsClient
    HttpClient httpsClient;
    Activity a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = this;



    }

    @Click(R.id.button2)
    void Scan(){

        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);

    }

    @Click(R.id.button)
    void SearchClick(){

        lista.setAdapter(new ListAdapter(a, null));
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(a, DetailActivity.class);
                i.putExtra("NOMBRE", "DEFAULT");
                i.putExtra("PERSONAS", 0);
                i.putExtra("ACTUALES", 0);
                i.putExtra("NINOS", 0);
                i.putExtra("MESA", 0);
                i.putExtra("ID", 0);
                a.startActivity(i);

            }
        });


    }


    @AfterInject
    @Background
    public void UserDataRequest(){
        try{

            HttpGet httpGet = new HttpGet("");
            HttpResponse response = httpsClient.execute(httpGet);
            ResponseAction(response);

        }catch(Exception e){



        }


    }

    @UiThread
    public void ResponseAction(HttpResponse response){


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            String contents = result.getContents();
            if (contents != null) {

                Intent i  = new Intent(a,DetailActivity.class);
                i.putExtra("NOMBRE","DEFAULT");
                i.putExtra("PERSONAS",0);
                i.putExtra("ACTUALES",0);
                i.putExtra("NINOS",0);
                i.putExtra("MESA",0);
                i.putExtra("ID",0);
                a.startActivity(i);



            } else {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        a);

                // set title
                alertDialogBuilder.setTitle("El código no pudo leerse");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Intente nuevamente")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                             dialog.dismiss();
                            }
                        });


                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                Log.v("", "FAIL");
            }
        }
    }
}
