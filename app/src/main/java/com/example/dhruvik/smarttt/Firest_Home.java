package com.example.dhruvik.smarttt;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Firest_Home extends AppCompatActivity  {


    Quiry_ID kam;
    Runnable r;
    Data d;
    String s_id;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firest__home);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

Quiry_ID kam = new Quiry_ID(this);
   if(Check()){  //thread code
       Helper helper = new Helper(this);

        Runnable r = new Runnable() {
           @Override
           public void run() {

               try {
                   Firebase.setAndroidContext(getApplicationContext());
                   Add_Permissiom.get_permission();
                   gooto();
                   sleep(4000);

               } catch (InterruptedException e) {
                   e.printStackTrace();
                   Intent open = new Intent(getApplicationContext(),Error_Help.class);
                   startActivity(open);
                   finish();
               }
               finally {
                   open_Activity();
               }
           }
       };


        Thread t = new Thread(r);
       t.start();

   }else{
       Intent open = new Intent(getApplicationContext(),First.class);
       startActivity(open);
   }
    }
    public Boolean Check(){

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        return info != null && info.isConnected();
    }


    public void setData(){
        Firebase.setAndroidContext(getApplicationContext());

        Url url = new Url(this);

             d = new Data();

            check_child();
    }
    private void open_Activity(){
        kam = new Quiry_ID(this);

        if(kam.Check_ID()){
            Intent open = new Intent(getApplicationContext(),Home.class);
            startActivity(open);
        }else{
            Intent open = new Intent(getApplicationContext(),JoinGroup.class);
            startActivity(open);
        }

    }
   public void gooto(){
       kam = new Quiry_ID(this);

       if(kam.Check_ID()){
           setData();
       }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

public boolean check_child(){
        boolean res =  true;

    s_id = kam.Get_ID();
    Firebase.setAndroidContext(getApplicationContext());

    Firebase firebase = new Firebase("https://smart-tt.firebaseio.com/"+s_id);
    firebase.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(Res.r) {
                if (dataSnapshot.hasChildren()) {
                    Change.Day = d.Day;
                    Home.Day = d.Day;
                } else {
                    Intent open = new Intent(getApplicationContext(),ErrorDATA.class);
                    startActivity(open);
                    finish();
                }
            }
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
        }
    });



    return res;
}
}