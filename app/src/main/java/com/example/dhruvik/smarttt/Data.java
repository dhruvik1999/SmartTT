package com.example.dhruvik.smarttt;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by dhruvik on 07-03-2018.
 */

public class Data {
    Firebase mRef;
    static String Sub[][] = new String[7][10];
    static String Day[] = new String[7];
        public Data() {
        mRef = new Firebase(Url.url);


        Runnable r1 = new Runnable() {
            @Override
            public void run() {

                try {
                    Log.i("start","work is about to start");
                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.child("sunday").child("sunday").getValue(String.class);
                            Day[0] = value;
                            value = dataSnapshot.child("monday").child("monday").getValue(String.class);
                            Day[1] = value;
                            value = dataSnapshot.child("tuesday").child("tuesday").getValue(String.class);
                            Day[2] = value;
                            value = dataSnapshot.child("wednesday").child("wednesday").getValue(String.class);
                            Day[3] = value;
                            value = dataSnapshot.child("thursday").child("thursday").getValue(String.class);
                            Day[4] = value;
                            value = dataSnapshot.child("friday").child("friday").getValue(String.class);
                            Day[5] = value;
                            value = dataSnapshot.child("saturday").child("saturday").getValue(String.class);
                            Day[6] = value;

                            try {
                                for (int i = 0; i < 7; i++) {

                                    int k = 0;
                                    String temp;
                                    char target = '|';
                                    temp = "\0";
                                    for (int j = 0; j < Day[i].length(); j++) {
                                        if (target == Day[i].charAt(j)) {
                                            if (temp == "\0")
                                                temp = "";

                                            Sub[i][k] = temp;
                                            k++;
                                            temp = "\0";
                                        } else
                                            temp = temp + Day[i].toString().charAt(j);
                                    }
                                }
                            }
                            catch (Exception e){

                            }
                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                        }
                    });
                }catch (Exception e){}
            }
        };



        Thread t1 = new Thread(r1);
        t1.start();
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
}

/*
mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("sunday").child("sunday").getValue(String.class);
                Day[0] = value;
                value = dataSnapshot.child("monday").child("monday").getValue(String.class);
                Day[1] = value;
                value = dataSnapshot.child("tuesday").child("tuesday").getValue(String.class);
                Day[2] = value;
                value = dataSnapshot.child("wednesday").child("wednesday").getValue(String.class);
                Day[3] = value;
                value = dataSnapshot.child("thursday").child("thursday").getValue(String.class);
                Day[4] = value;
                value = dataSnapshot.child("friday").child("friday").getValue(String.class);
                Day[5] = value;
                value = dataSnapshot.child("saturday").child("saturday").getValue(String.class);
                Day[6] = value;
                Log.i("ckeck","1");
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
 */