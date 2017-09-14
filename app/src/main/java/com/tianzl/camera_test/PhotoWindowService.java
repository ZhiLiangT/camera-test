package com.tianzl.camera_test;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class PhotoWindowService extends Service {


    private MyPhotoWindowManager myWindowManager;

    @Override
    public IBinder onBind(Intent intent) {
        return new myServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myWindowManager = new MyPhotoWindowManager();
        createWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent notificationIntent =new Intent(this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,0);
        Notification.Builder builder=new Notification.Builder(this);
        builder.setContentTitle("前台服务通知的标题");
        builder.setContentText("前台服务通知的内容");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);//设置点击通知后的操作

        Notification notification = builder.getNotification();//将Builder对象转变成普通的notification
        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void createWindow() {
        // 当前界面是桌面，且没有悬浮窗显示，则创建悬浮窗。
        myWindowManager.removeSmallWindow(getApplicationContext());
        myWindowManager.createSmallWindow(getApplicationContext());

    }


    public class myServiceBinder extends Binder {
        public void startCarema() {
            myWindowManager.startCarema();
        }
        public void stopCarema() {
            myWindowManager.stopCarema();
        }
    }
}
