package com.example.dhruvik.smarttt;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class OptCreat extends AppCompatActivity {

    Quiry_ID kam;
    boolean cc[] = {false,false,false,false,false,false,false};
    String Day[] = new String[7];
    String Sub[][] = new String[7][10];
    int day_dig=0;
    String url = "https://smart-tt.firebaseio.com/";
    Spinner spinner;
    Button save,create;
    EditText lac1,lac2,lac3,lac4,lac5,lac6,lac7,lac8,lac9,lac10;
    String day[]={"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};
    ArrayAdapter<String> adapter ;
   String sub[] = new String[7];
   Intermidiate trans;
   boolean per = true;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opt_creat);
       this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Initiallization();

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,day);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day_dig = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cc[day_dig] = true;
                takeData();
                 saveData();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(per) {
                    WindUp();
                }

                Res.r = false;
                Toast.makeText(getApplicationContext(),"Yout Time-table is Created",Toast.LENGTH_SHORT);
                Intent share = new Intent(getApplicationContext(),Share.class);
                startActivity(share);

                finish();
            }
        });

    }
    public void Initiallization(){
        spinner = (Spinner)findViewById(R.id.spinner);
       save = (Button)findViewById(R.id.save);
       create = (Button)findViewById(R.id.create);
       lac1 = (EditText)findViewById(R.id.lac_1);
       lac2 = (EditText)findViewById(R.id.lac_2);
        lac3 = (EditText)findViewById(R.id.lac_3);
        lac4 = (EditText)findViewById(R.id.lac_4);
        lac5 = (EditText)findViewById(R.id.lac_5);
        lac6 = (EditText)findViewById(R.id.lac_6);
        lac7 = (EditText)findViewById(R.id.lac_7);
        lac8 = (EditText)findViewById(R.id.lac_8);
        lac9 = (EditText)findViewById(R.id.lac_9);
        lac10 = (EditText)findViewById(R.id.lac_10);

        kam = new Quiry_ID(this);
    }

    public void takeData(){
        sub[day_dig] =
                lac1.getText().toString()+ "|" + lac2.getText().toString()+ "|" +lac3.getText().toString()+ "|" + lac4.getText().toString()+ "|" +
                lac5.getText().toString()+ "|" +lac6.getText().toString()+ "|" +lac7.getText().toString()+ "|" +lac8.getText().toString()+ "|" +
                lac9.getText().toString()+ "|" +lac10.getText().toString()+ "|" ;

        lac1.setText("");lac2.setText("");lac3.setText("");lac4.setText("");lac5.setText("");
        lac10.setText("");lac9.setText("");lac8.setText("");lac7.setText("");lac6.setText("");
    }
    public void saveData(){ //code for data save in firebase

        trans = new Intermidiate();
        switch (day_dig)
        {
            case 0:
                trans.setMonday(sub[day_dig]);
                 break;

            case 1:
                trans.setTuesday(sub[day_dig]);
                break;

            case 2:
                trans.setWednesday(sub[day_dig]);
                break;

            case 3:
                trans.setThursday(sub[day_dig]);
                break;

            case 4:
                trans.setFriday(sub[day_dig]);
                break;

            case 5:
                trans.setSaturday(sub[day_dig]);
                break;

            case 6:
                trans.setSunday(sub[day_dig]);
                break;
        }
        Firebase.setAndroidContext(getApplicationContext());
        Firebase firebase = new Firebase(url);

        firebase.child(kam.Get_ID()).child(day[day_dig]).setValue(trans);//change for idddd...............got id frome QuiryID
        Toast.makeText(getApplicationContext(),"Your"+day[day_dig]+"'s data is save.",Toast.LENGTH_LONG).show();
    }
    public void WindUp(){// code to check the data and if anr raw is empty then and default data
        per = false;
        for(int i=0;i<7;i++){
            if(!cc[i]){
                day_dig = i;
                sub[day_dig] = "||||||||||";
                saveData();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Res.r = false;

        if(per) {
            WindUp();
        }

        finish();
    }


}
