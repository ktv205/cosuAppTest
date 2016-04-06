package com.example.tejavelagapudi.cosuapp;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();
    }


    //You dont have to call the startLockTask if you have declared if_whitelisted in the manifest

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void enableLockState(View view) {
        if (!checkLockState()) {
            startLockTask();
        }

    }


    // I am just calling stopLockTask when clicking on a button
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void disableLockState(View view) {
        if (checkLockState()) {
            try {
                stopLockTask();
            }catch (SecurityException e){
                Log.d(TAG,"securityException->"+e.getLocalizedMessage());
            }

        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean checkLockState() {
        boolean isLocked = false;
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        try {

            if (am.getLockTaskModeState() == ActivityManager.LOCK_TASK_MODE_NONE) {
                Log.d(TAG, "lock state is not enabled");
            } else {
                Log.d(TAG, "lock state is enabled");
                isLocked = true;
            }
        } catch (Exception e) {
            Log.d(TAG, "exception caught");
        }
        return isLocked;
    }

    public boolean checkKeyguard() {
        boolean isEnabled = false;
        return isEnabled;
    }
}
