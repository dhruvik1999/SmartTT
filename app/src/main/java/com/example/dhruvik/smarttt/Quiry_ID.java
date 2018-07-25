package com.example.dhruvik.smarttt;

import android.content.Context;

/**
 * Created by dhruvik on 09-03-2018.
 */

public class Quiry_ID {
    Helper database;
    public Quiry_ID(Context context){
        //code foe exicuting the SQLite database
        database = new Helper(context);
    }

    public Boolean Check_ID(){ //code for chek the id is exist in SQLite database
        boolean result = true;
       result =  database.check();
        return result;
    }

    public void Save_ID(String status,String id){//code for the save the id in SQLite Database in (0,0) cordinate
        database.saveData(status,id);
    }


    public String Get_ID(){
        String ID ;//= "imformp";
            ID = database.takeData(1);
        return ID;
    }

    public String Get_STATUS(){
        String STATUS ;//= "imformp";
        STATUS = database.takeData(0);
        return STATUS;
    }

    public String Check_User_Status(){ //code for the check the luser status of the user status
        String user = "user";

        if(Check_ID()){
            user = "user";
        }else{
            user = "create";
        }

        return user;
    }

    public void deleteTable(){
        database.deleteTable(database.takeData(0),Get_ID());
    }

}