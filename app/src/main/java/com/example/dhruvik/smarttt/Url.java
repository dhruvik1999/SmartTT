package com.example.dhruvik.smarttt;

import android.content.Context;



public class Url {
    public static String url_monday;
    public static String url_tuesday;
    public static String url_wednesday;
    public static String url_thursday;
    public static String url_friday;
    public static String url_saturday;
    public static String url_sunday;
    public static String url ;

   //static Context context;
    public Url(Context context) {
        //   this.context = context;
        Quiry_ID kam = new Quiry_ID(context);
        String ref = kam.Get_ID();

        //static Quiry_ID kam = new Quiry_ID(context);
       //   static String ref = kam.Get_ID();

        url = "https://smart-tt.firebaseio.com/" + ref;
        url_sunday="https://smart-tt.firebaseio.com/" + ref + "/sunday/sunday";
        url_monday = "https://smart-tt.firebaseio.com/" + ref + "/monday/monday";
        url_tuesday = "https://smart-tt.firebaseio.com/" + ref + "/tuesday/tuesday";
        url_wednesday = "https://smart-tt.firebaseio.com/" + ref + "/wednesday/wednesday";
        url_thursday = "https://smart-tt.firebaseio.com/" + ref + "/thursday/thursday";
         url_friday = "https://smart-tt.firebaseio.com/" + ref + "/friday/friday";
         url_saturday = "https://smart-tt.firebaseio.com/" + ref + "/saturday/saturday";
    }
}
