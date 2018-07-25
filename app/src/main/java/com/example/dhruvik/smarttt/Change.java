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
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Change extends AppCompatActivity {

    Quiry_ID kam;
    String ret;
    String url = "https://smart-tt.firebaseio.com/";
    static  String Day[] = new String[7];
    String Sub[][] = new String[7][10];
    int day_dig=0;
    Spinner spinner;
    Button save,create;
    EditText lac1,lac2,lac3,lac4,lac5,lac6,lac7,lac8,lac9,lac10;
    String day[]={"sunday","monday","tuesday","wednesday","thursday","friday","saturday"};
    ArrayAdapter<String> adapter ;
    String sub[] = new String[7];
    Intermidiate trans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Initiallization();
        Firebase.setAndroidContext(getApplicationContext());
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,day);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //TakeData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day_dig = i;
                    setSub(day_dig);
                    setDay(day_dig);
                    create.setText("save Change");
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Res.r = false;
                takeData();
                 try {
                     saveData();
                     Toast.makeText(getApplicationContext(),"Your data is save successfully",Toast.LENGTH_SHORT).show();
                 }catch (Exception e){
                     Intent open =  new Intent(getApplicationContext(),Error_Help.class);
                     startActivity(open);
                     finish();
                 }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Res.r = false;
                Toast.makeText(getApplicationContext(),"Yout Time-table is save",Toast.LENGTH_SHORT);
                Intent open = new Intent(getApplicationContext(),Home.class);
                startActivity(open);
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
     String go = lac1.getText().toString()+ "|" + lac2.getText().toString()+ "|" +lac3.getText().toString()+ "|" + lac4.getText().toString()+ "|" +
                        lac5.getText().toString()+ "|" +lac6.getText().toString()+ "|" +lac7.getText().toString()+ "|" +lac8.getText().toString()+ "|" +
                        lac9.getText().toString()+ "|" +lac10.getText().toString()+ "|" ;
        sub[day_dig] = go;
        lac1.setText("\0");lac2.setText("\0");lac3.setText("\0");lac4.setText("\0");lac5.setText("\0");
        lac10.setText("\0");lac9.setText("\0");lac8.setText("\0");lac7.setText("\0");lac6.setText("\0");
    }
    public void saveData(){ //code for data save in firebase
        trans = new Intermidiate();
        switch (day_dig)
        {
            case 1:
                trans.setMonday(sub[day_dig]);
                break;

            case 2:
                trans.setTuesday(sub[day_dig]);
                break;

            case 3:
                trans.setWednesday(sub[day_dig]);
                break;

            case 4:
                trans.setThursday(sub[day_dig]);
                break;

            case 5:
                trans.setFriday(sub[day_dig]);
                break;

            case 6:
                trans.setSaturday(sub[day_dig]);
                break;

            case 0:
                trans.setSunday(sub[day_dig]);
                break;
        }
        Firebase firebase = new Firebase(url);
        firebase.child(kam.Get_ID()).child(day[day_dig]).setValue(trans);
        Toast.makeText(getApplicationContext(),"Your daata is save.",Toast.LENGTH_LONG).show();
    }

    public void TakeData(){
      Firebase.setAndroidContext(getApplicationContext());
        Data d = new Data();
        Day = d.Day;
    }

    public void setSub(int i) {
        int k = 0;
        String temp;
        char target = '|';
        temp = "";
        try {

          /*  for (int j = 0; j < Day[i].length(); j++) {
                if (target == Day[i].charAt(j)) {

                    if (temp == "")
                        temp = "-";

                    Sub[i][k] = temp;
                    k++;
                    temp = "";
                } else
                    temp = temp + Day[i].toString().charAt(j);
            }
            */
          Sub = Data.Sub;

        }catch (Exception e){

            Intent open = new Intent(getApplicationContext(),ErrorDATA.class);
            startActivity(open);
            finish();

        }
    }
    public void setDay(int t){
        //opretion on day[t] and remove all the name of subject by the order and store in aub[t][1-10]
        lac1.setText(Sub[t][0]);
        lac2.setText(Sub[t][1]);
        lac3.setText(Sub[t][2]);
        lac4.setText(Sub[t][3]);
        lac5.setText(Sub[t][4]);
        lac6.setText(Sub[t][5]);
        lac7.setText(Sub[t][6]);
        lac8.setText(Sub[t][7]);
        lac9.setText(Sub[t][8]);
        lac10.setText(Sub[t][9]);
    }
}