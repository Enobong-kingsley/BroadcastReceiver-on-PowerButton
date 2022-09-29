package com.example.bradcastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ScreenOnOffReceiver screenOnOffReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("BroadcastReceiver Running After App Exit.");
        // Create an IntentFilter instance.
        IntentFilter intentFilter = new IntentFilter();

        // Add network connectivity change action.
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");

        // Set broadcast receiver priority.
        intentFilter.setPriority(100);

        // Create a network change broadcast receiver.
        screenOnOffReceiver = new ScreenOnOffReceiver();

        // Register the broadcast receiver with the intent filter object.
        registerReceiver(screenOnOffReceiver, intentFilter);

        Toast.makeText(this, ScreenOnOffReceiver.SCREEN_TOGGLE_TAG, Toast.LENGTH_SHORT).show();
        Log.d(ScreenOnOffReceiver.SCREEN_TOGGLE_TAG, "onCreate: screenOnOffReceiver is registered.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister screenOnOffReceiver when destroy.
        if(screenOnOffReceiver!=null)
        {
            unregisterReceiver(screenOnOffReceiver);
            Log.d(ScreenOnOffReceiver.SCREEN_TOGGLE_TAG, "onDestroy: screenOnOffReceiver is unregistered.");
        }
    }
}