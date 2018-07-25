package com.example.dhruvik.smarttt;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Share extends AppCompatActivity {
    Button share;
    TextView id;
    Quiry_ID kam;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

kam = new Quiry_ID(this);
        Initiallization();
        id.setText("id: " + kam.Get_ID());
        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                    Share();
            }
        });
    }

    private void Initiallization(){
        share = (Button)findViewById(R.id.id);
        id = (TextView)findViewById(R.id.shareId);
        kam = new Quiry_ID(this);
            }

    public void Share(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String data = "Your class made group in Smart TT application.your admin wants to you join this class. Now all the update"
                +"related to your class time tabe updated in Smart TT. so,you should join your class usin this  \n id:"+ kam.Get_ID()+"\n" +
                "share link :: "+Add_Permissiom.link_share;
        shareIntent.putExtra(Intent.EXTRA_TEXT, data);
        startActivity(Intent.createChooser(shareIntent,"Share using"));
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        Intent open = new Intent(getApplicationContext(),Firest_Home.class);
        startActivity(open);
    }
}
