package com.example.milos.mapcontacts;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Milos on 26-Jul-17.
 */

public class SplashActivity extends AppCompatActivity {
    private String str = null;
    private ProgressDialog dialog;
    private String[] wordSplit;
    static ArrayList<InfoModel> info = new ArrayList<InfoModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new getInfo().execute();
    }

    public class getInfo extends AsyncTask<Object, Object, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(SplashActivity.this);
            dialog.setMessage("Please wait");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Object... params) {
            try {
                URL url = new URL("http://www.cs.columbia.edu/~coms6998-8/assignments/homework2/contacts/contacts.txt");

                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));


                //SglHelper sglHelper = new SglHelper(getApplicationContext());


                while ((str = in.readLine()) != null) {
                    wordSplit = str.split(" ");

                    InfoModel infoModel = new InfoModel();
                    infoModel.setName_(wordSplit[0]);
                    infoModel.setEmailAddress_(wordSplit[1]);
                    infoModel.setLatitude_(wordSplit[2]);
                    infoModel.setLongitude_(wordSplit[3]);
                    // sglHelper.addContact(infoModel);
                    Data.infoData.add(infoModel);
                }
                //Data.infoData.clear();

                in.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // if (dialog.isShowing()) {
            //  dialog.dismiss();
            Intent intent = new Intent(SplashActivity.this, MapsActivity.class);
            startActivity(intent);
            //}
        }
    }
}