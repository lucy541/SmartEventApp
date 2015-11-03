package com.event.smart.smarteventapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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


import com.google.gson.Gson;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.HttpsClient;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

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
    @RootContext
    Context context;
    @Bean
    Gson gson;


   // @Rest(rootUrl = "http://services.smartevent.mx" , converters = { MappingJackson2HttpMessageConverter.class })
    public interface SearchCall{

        @Get("/")
        List <Guest>search(String name);


    }

    @Click(R.id.button2)
    void Scan(){

        IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
        integrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);

    }

    @Click(R.id.button)
    void SearchClick(){

        lista.setAdapter(new ListAdapter(context, null));
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra("NOMBRE", "DEFAULT");
                i.putExtra("PERSONAS", 0);
                i.putExtra("ACTUALES", 0);
                i.putExtra("NINOS", 0);
                i.putExtra("MESA", 0);
                i.putExtra("ID", 0);
                context.startActivity(i);

            }
        });


    }





    @AfterInject
    @Background
    public void UserDataRequest(String code){
        try{

            HttpGet httpGet = new HttpGet("http://services.smartevent.mx/validate?code="+code);
            HttpResponse response = httpsClient.execute(httpGet);
            ResponseAction(response);

        }catch(Exception e){



        }


    }


    public void openDetails(Guest guest){

        Intent i = new Intent(context, DetailActivity.class);
        i.putExtra("NOMBRE", guest.getName());
        i.putExtra("PERSONAS", guest.getAdults());
        i.putExtra("ACTUALES", guest.getKids());
        i.putExtra("NINOS", guest.getKids());
        i.putExtra("MESA", guest.getAdults());
        i.putExtra("ID", guest.getId());
        context.startActivity(i);



    }

    @UiThread
    public void ResponseAction(HttpResponse response){

        try {
            Guest  guest = gson.fromJson(EntityUtils.toString(response.getEntity()),Guest.class);

            openDetails(guest);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (result != null) {
            String contents = result.getContents();
            if (contents != null) {


                UserDataRequest(contents);


            } else {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

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
