package com.example.dhruvik.smarttt;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ErrorDATA extends AppCompatActivity {

    Button next,retry;
    Quiry_ID kam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_dat);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        kam = new Quiry_ID(this);
        next = (Button) findViewById(R.id.next);
        retry = (Button) findViewById(R.id.retry);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(),JoinGroup.class);
                startActivity(open);
                kam.deleteTable();
                finish();
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Firest_Home.class));
            }
        });
    }

    @Override
    protected void onPause() {
        kam.deleteTable();
        super.onPause();
    }
}
