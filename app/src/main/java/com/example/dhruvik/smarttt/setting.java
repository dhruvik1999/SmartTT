package com.example.dhruvik.smarttt;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.nsd.NsdManager;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;
public class setting extends AppCompatActivity {

    Button change,share,delete,help,about,lieve;
    Quiry_ID kam;
    Home home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Initiallization();

        home = new Home();

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(),Change.class);
                startActivity(open);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent open  = new Intent(getApplicationContext(),Share.class);
             startActivity(open);
            }
        });

        lieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//coded for delete table of SQLite in android mobile

                dlog("Are you want to leave the class?",0);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("My name is dhruvik",kam.Get_STATUS());
                dlog("are you want to delete the class",2);

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open = new Intent(getApplicationContext(),About.class);
                startActivity(open);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open  = new Intent(getApplicationContext(),Help.class);
                startActivity(open);
            }
        });


    }

    public void Initiallization(){
        change = (Button) findViewById(R.id.change);
         share = (Button) findViewById(R.id.share);
          delete = (Button) findViewById(R.id.delete);
           help = (Button) findViewById(R.id.help);
            about = (Button) findViewById(R.id.about);
            lieve = (Button) findViewById(R.id.lieve);
            kam = new Quiry_ID(this);
    }
    public void deleteData(String id){
        Firebase da = new Firebase("https://smart-tt.firebaseio.com/"+id);
        da.removeValue();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent open = new Intent(getApplicationContext(),Home.class);
        startActivity(open);
    }

    public void dlog(String msg,final int ds){
        AlertDialog.Builder builder = new AlertDialog.Builder(setting.this);
        builder.setMessage(msg).setCancelable(false).setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               if(ds==0) {
                   kam.deleteTable();
                   Intent open = new Intent(getApplicationContext(), Firest_Home.class);
                   startActivity(open);
                   finish();
               }else {

                   if(kam.Get_STATUS().equals("admin")) {

                       deleteData(kam.Get_ID());
                       kam.deleteTable();
                       Intent open = new Intent(getApplicationContext(), Firest_Home.class);
                       startActivity(open);
                       finish();
                   }else{
                       Toast.makeText(getApplicationContext(),"You are not admin \n you can't delete the group",Toast.LENGTH_LONG).show();
                   }
               }
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog dil =  builder.create();
        dil.setTitle("Aleart");
       dil.show();
    }
}
