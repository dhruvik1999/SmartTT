package com.example.dhruvik.smarttt;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String peragraph_1 = "SMART TT";
        String peragraph_2 = "";
        String peragraph_3 = "Dhruvik Navadiya";

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(peragraph_1+"\n"+"\n"+peragraph_2+"\n"+"\n"+peragraph_3);
    }
}
