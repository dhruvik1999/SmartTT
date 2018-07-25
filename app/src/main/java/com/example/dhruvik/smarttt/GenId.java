package com.example.dhruvik.smarttt;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.zip.DataFormatException;

public class GenId extends AppCompatActivity {

    EditText mobil;
    Button create;
    String ID;
    Quiry_ID kam;
    ProgressBar gk;
    Res kk;
    boolean result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_id);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        kk = new Res();
        Initiallization();
        gk.setVisibility(View.GONE);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ID = mobil.getText().toString();
                gk.setVisibility(View.VISIBLE);


                Firebase.setAndroidContext(getApplicationContext());
                Firebase firebase = new Firebase("https://smart-tt.firebaseio.com/" + ID);

                firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(Res.r) {
                            if (dataSnapshot.hasChildren()) {
                                Intent open = new Intent(getApplicationContext(),JoinGroup.class);
                                startActivity(open);
                                Toast.makeText(getApplicationContext(), "This class already exists. \n you have to join this class.", Toast.LENGTH_LONG).show();
                            } else {
                                kam.Save_ID("admin", ID);
                                Intent open = new Intent(getApplicationContext(), OptCreat.class);
                                startActivity(open);

                                finish();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
            }
        });
    }
    public void Initiallization(){
        mobil = (EditText)findViewById(R.id.MobileNo);
        create = (Button)findViewById(R.id.create);
        kam = new Quiry_ID(this);
        gk = (ProgressBar) findViewById(R.id.gk);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent open = new Intent(getApplicationContext(),JoinGroup.class);
        startActivity(open);
    }
}