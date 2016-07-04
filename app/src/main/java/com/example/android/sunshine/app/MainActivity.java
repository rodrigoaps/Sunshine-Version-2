package com.example.android.sunshine.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.net.*;
import java.io.*;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.os.AsyncTask;

import com.example.android.sunshine.app.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MainActivity", "Create");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v("MainActivity", "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("MainActivity", "Stop");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("MainActivity", "Resume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("MainActivity", "Start");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("MainActivity", "Destroy");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if (id == R.id.action_map) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            String location = prefs.getString(this.getString(R.string.pref_location_key), this.getString(R.string.pref_location_default));
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                    .appendQueryParameter("q", location)
                    .build();
            intent.setData(geoLocation);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}