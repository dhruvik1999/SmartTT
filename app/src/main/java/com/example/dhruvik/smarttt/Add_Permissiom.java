package com.example.dhruvik.smarttt;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Add_Permissiom {
    static boolean permiss_banner = true;
    static  boolean permiss_int = true;
    static String link_share = "smart tt";
    static String banner_id = "ca-app-pub-3940256099942544/6300978111";
    static String int_id = "ca-app-pub-3940256099942544/1033173712";
    static String int1_id = "ca-app-pub-3940256099942544/1033173712";
    static String app_id = "ca-app-pub-3940256099942544~3347511713";


    public static void get_permission(){//get all permission from firebase and assign the permission
        Firebase firebase = new Firebase("https://smart-tt.firebaseio.com/Permission");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("i_id").getValue(String.class);
                int_id = value;
                String value2 = dataSnapshot.child("i2").getValue(String.class);
                int1_id = value2;
                String value3 = dataSnapshot.child("link").getValue(String.class);
                link_share = value3;
                String value4 = dataSnapshot.child("b_id").getValue(String.class);
                banner_id = value4;
                String value5 = dataSnapshot.child("a_id").getValue(String.class);
                app_id = value5;

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
