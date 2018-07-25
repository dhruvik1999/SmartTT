package com.example.dhruvik.smarttt;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Notification_reciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Intent open = new Intent(context,Firest_Home.class);
        open.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,open,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.newico)
                .setContentTitle("Check your today time-table..")
                .setContentText("click to check")
                .setAutoCancel(true);

        notificationManager.notify(100,builder.build());

    }
}
/*
   NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    Notification notification = new Notification(R.drawable.notification_icon, "Notify Alarm strart", System.currentTimeMillis());
    Intent myIntent = new Intent(this , MyActivity.class);
    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent, 0);
    notification.setLatestEventInfo(this, "Notify label", "Notify text", contentIntent);
    mNM.notify(NOTIFICATION, notification);

 */