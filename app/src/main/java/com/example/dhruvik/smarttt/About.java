package com.example.dhruvik.smarttt;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

      /*  String paragraph_1= "This applcation is help to students who are currently in regular class system";
       String paragraph_2= "you can create the group and join the group";
        String paragraph_3= "if you create the group then you are the admin for this only group.you have all the permission about change" +
                "the subjects and delete the group";
        String paragraph_4="You as Admin can't leave the group.you can only delete the group"+"if you delete the group then all the members " +
                "autometically leave the group.";
        String paragraph_5= "if you are not admin then you can't change the subject and can't delete the group.you only check the TIME TABLE and you can " +
                "quite the group if you class is change";

*/
        TextView tv = (TextView)findViewById(R.id.textView);
    //    tv.setText(paragraph_1+"\n"+"\n"+paragraph_2+"\n"+"\n"+paragraph_3+"\n"+"\n"+paragraph_4+"\n"+"\n"+paragraph_5);
    }
}
