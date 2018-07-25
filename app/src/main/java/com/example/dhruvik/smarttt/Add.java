package com.example.dhruvik.smarttt;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Add {
    static Context context;
    static AdRequest adRequest1,adRequest2,adRequest3,adRequest4,adRequest5,adRequest6,adRequest7;
    static InterstitialAd interstitialAd;
    public Add(Context context){
        this.context = context;
    }
    public static void bannerLoad(){
        adRequest1 = new AdRequest.Builder().build();
        adRequest2 = new AdRequest.Builder().build();
        adRequest3 = new AdRequest.Builder().build();
        adRequest4 = new AdRequest.Builder().build();
        adRequest5 = new AdRequest.Builder().build();
        adRequest6 = new AdRequest.Builder().build();
        adRequest7 = new AdRequest.Builder().build();
    }
    public static void intLoad(){
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        interstitialAd = new InterstitialAd(context);
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

}
