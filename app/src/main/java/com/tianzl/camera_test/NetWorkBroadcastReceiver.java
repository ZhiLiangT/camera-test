package com.tianzl.camera_test;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Toast;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Created by tianzl on 2017/9/14.
 */

public class NetWorkBroadcastReceiver extends BroadcastReceiver {
    private PhotoWindowService.myServiceBinder binder;
    private MyServiceConn conn;
    @Override
    public void onReceive(Context context, Intent intent) {
            // usb 插入
            Toast.makeText(context, "网络变化", Toast.LENGTH_LONG).show();
            conn = new MyServiceConn();
            Intent mBootIntent = new Intent(context, PhotoWindowService.class);
            context.getApplicationContext(). startService(mBootIntent);
            context.getApplicationContext().bindService(mBootIntent, conn, BIND_AUTO_CREATE);
    }
    private class MyServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (PhotoWindowService.myServiceBinder) service;
            binder.startCarema();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

    }
}
