package com.example.dhruvik.smarttt;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class JoinGroup extends AppCompatActivity {

    Button create,join;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Initiallization();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_notification();
                Intent oepnOptCreate = new Intent(getApplicationContext(),GenId.class);
                startActivity(oepnOptCreate);

                finish();
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_notification();
                Intent oepnOptJoin = new Intent(getApplicationContext(),OptJoin.class);
                startActivity(oepnOptJoin);

                finish();
            }
        });
    }

    public void Initiallization(){
        create = (Button)findViewById(R.id.create);
        join = (Button)findViewById(R.id.join);
    }

    public void my_notification(){
        Intent myIntent = new Intent(this , Notification_reciver.class);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, myIntent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 10);
        calendar.set(Calendar.HOUR, 12);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24 , pendingIntent);
    }


}
