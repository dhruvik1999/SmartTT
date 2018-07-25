package com.example.dhruvik.smarttt;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class OptJoin extends AppCompatActivity {

    EditText id;
    Button join;
    ProgressBar pb;

   static String s_id;
   Quiry_ID kam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt_join);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        kam = new Quiry_ID(this);
        Initiallization();
        pb.setVisibility(View.GONE);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pb.setVisibility(View.VISIBLE);
                s_id = id.getText().toString();
                Firebase.setAndroidContext(getApplicationContext());

                Firebase firebase = new Firebase("https://smart-tt.firebaseio.com/"+s_id);
                firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(Res.r) {
                            if (dataSnapshot.hasChildren()) {
                                try {
                                    kam.Save_ID("user", s_id);
                                    Toast.makeText(getApplicationContext(), "you join your class succusessfully", Toast.LENGTH_LONG).show();
                                    Intent open = new Intent(getApplicationContext(), Firest_Home.class);
                                    startActivity(open);

                                } catch (Exception e) {
                                    Intent open = new Intent(getApplicationContext(), Error_Help.class);
                                    startActivity(open);

                                    kam.deleteTable();
                                    finish();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Your id is not Correct \n plz try again", Toast.LENGTH_LONG).show();

                                Intent open = new Intent(getApplicationContext(), JoinGroup.class);
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
        id = (EditText) findViewById(R.id.id);
        join = (Button) findViewById(R.id.butjoin);
        pb = (ProgressBar)findViewById(R.id.lol);
    }
    public boolean Check_id(){
      return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
