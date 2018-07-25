package com.example.dhruvik.smarttt;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.sip.SipSession;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.text.DateFormat;
import java.util.Calendar;

public class Home extends AppCompatActivity {

    TextView day,lac1,lac2,lac3,lac4,lac5,lac6,lac7,lac8,lac9,lac10;
    Button change,next;
    String var[] = {"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
    static String Sub[][] = new String[7][10];
    static String Day[] = new String[7];
    int dayCount = -1;
    Quiry_ID kam;
    AdView av,av1,av2;
    AdRequest adRequest4,adRequest5,adRequest6,adRequest7,adRequest8,adRequest9,adRequest10;
    private InterstitialAd interstitialAd;
    private InterstitialAd interstitialAd1;
    Boolean pp = true;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_home);
           this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

           Initialization();

           MobileAds.initialize(this,Add_Permissiom.app_id);
           interstitialAd.setAdUnitId(Add_Permissiom.int_id);
           interstitialAd1.setAdUnitId(Add_Permissiom.int1_id);

           Add.bannerLoad();

           intLoad();
           Firebase.setAndroidContext(getApplicationContext());
           SetDefault();

           if(check_user_status()) //if user is new then go to creat othervise or joinning the group
           {
               Intent joinGroup = new Intent(getApplicationContext(), JoinGroup.class);
               startActivity(joinGroup);
           }

           change.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent open = new Intent(getApplicationContext(),setting.class);
                   startActivity(open);
               }
           });

           interstitialAd1.setAdListener(new AdListener(){
               @Override
               public void onAdClosed() {
                   super.onAdClosed();
                   finish();
               }
           });

           interstitialAd.setAdListener(new AdListener(){
               @Override
               public void onAdLoaded() {
                   super.onAdLoaded();
                   pp = false;
               }
           });
       }

       public Boolean check_user_status(){ //code for check user_status if user is admin||user then return false other-wise return true
           Boolean result=!true;
           return result;
       }

       public void Initialization(){

              day = (TextView)findViewById(R.id.day);
               lac1= (TextView)findViewById(R.id.lac_1);
               lac2= (TextView)findViewById(R.id.lac_2);
               lac3= (TextView)findViewById(R.id.lac_3);
               lac4= (TextView)findViewById(R.id.lac_4);
               lac5= (TextView)findViewById(R.id.lac_5);
               lac6= (TextView)findViewById(R.id.lac_6);
               lac7= (TextView)findViewById(R.id.lac_7);
               lac8= (TextView)findViewById(R.id.lac_8);
               lac9= (TextView)findViewById(R.id.lac_9);
               lac10= (TextView)findViewById(R.id.lac_10);
               change = (Button)findViewById(R.id.change);

           //     av = (AdView)findViewById(R.id.adView);
                kam = new Quiry_ID(this);
                interstitialAd = new InterstitialAd(this);
           interstitialAd1 = new InterstitialAd(this);

           av = new AdView(this);
           av.setAdUnitId(Add_Permissiom.banner_id);
           av.setAdSize(AdSize.BANNER);
           LinearLayout ll = (LinearLayout)findViewById(R.id.ll);

           AdRequest ar = new AdRequest.Builder().build();
           av.loadAd(ar);
           ll.addView(av);

           }

       public void TakeData(){
           //activity data abstaraction from the onlione firebase daatabase
           Firebase.setAndroidContext(getApplicationContext());
           Data d = new Data();
           Day = d.Day;
           }

       public void setDay(int t){
           //opretion on day[t] and remove all the name of subject by the order and store in aub[t][1-10]
           lac1.setText("1:  "+Sub[t][0]);
           lac2.setText("2:  "+Sub[t][1]);
           lac3.setText("3:  "+Sub[t][2]);
           lac4.setText("4:  "+Sub[t][3]);
           lac5.setText("5:  "+Sub[t][4]);
           lac6.setText("6:  "+Sub[t][5]);
           lac7.setText("7:  "+Sub[t][6]);
           lac8.setText("8:  "+Sub[t][7]);
           lac9.setText("9:  "+Sub[t][8]);
           lac10.setText("10:  "+Sub[t][9]);
       }

       public void setSub(int i){
           int k=0;
           String temp;
           char target = '|';
           temp ="\0";
             try {


              /*   for (int j = 0; j < Day[i].length(); j++) {
                     if (target == Day[i].charAt(j)) {
                         if (temp == "\0")
                             temp = "";

                         Sub[i][k] = temp;
                         k++;
                         temp = "\0";
                     } else
                         temp = temp + Day[i].toString().charAt(j);
                         }
                */

          Sub = Data.Sub;

             }catch (Exception e){
                 Log.i("Action","Inligall Action");
                 kam.deleteTable();

                 Intent open = new Intent(getApplicationContext(),ErrorDATA.class);
                 startActivity(open);
                 finish();
             }
       }
       public void SetDefault(){
           dayCount++;
           dayCount = dayCount%7;
           day.setText(var[dayCount]);
           setSub(dayCount);
           setDay(dayCount);
       }

    @Override
    protected void onPause() {
        super.onPause();
        if(pp) {
            finish();
        }
    }
    public void tip(View view){
           pp = true;
        dayCount++;
        dayCount = dayCount%7;
        day.setText(var[dayCount]);
        setSub(dayCount);
        setDay(dayCount);

        if(Add_Permissiom.permiss_banner) {
            if (dayCount == 0) {
                av.loadAd(Add.adRequest1);
            } else if (dayCount == 1)
                av.loadAd(Add.adRequest2);
            else if (dayCount == 2)
                av.loadAd(Add.adRequest3);
            else if (dayCount == 3)
                av.loadAd(Add.adRequest4);
            else if (dayCount == 4)
                av.loadAd(Add.adRequest5);
            else if (dayCount == 5)
                av.loadAd(Add.adRequest6);
            else if (dayCount == 6)
                av.loadAd(Add.adRequest7);
        }

        if(dayCount == dcid()){
            if(interstitialAd.isLoaded()&&Add_Permissiom.permiss_int){
                interstitialAd.show();
                pp = false;
                intLoad();
            }
        }
    }
    public void  intLoad(){
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd1.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
        if(interstitialAd1.isLoaded()&&Add_Permissiom.permiss_int){
            interstitialAd1.show();
            pp = false;
        }else {
            super.onBackPressed();
        }

    }
    public int dcid(){//code for which day show int add
           int dcid = 3;

        Calendar calendar = Calendar.getInstance();
      dcid = calendar.get(Calendar.DAY_OF_WEEK);
           return dcid-1;
    }
}

