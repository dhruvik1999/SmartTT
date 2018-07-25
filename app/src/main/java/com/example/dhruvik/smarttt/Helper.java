package com.example.dhruvik.smarttt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dhruvik on 16-03-2018.
 */

public class Helper extends SQLiteOpenHelper {
    static String Table_Name = "smartTT";
    static String status= "STATUS";
    static String id = "ID";
    long dis = -1;
    public Helper(Context context) {
        super(context, "Dream",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+Table_Name+" (STATUS TEXT,ID TEST)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name);
        onCreate(db);
    }

    public String takeData(int position){
     String result_Status = "user/admin";
     String result_id= "imfo";
     String result = "final result";

        SQLiteDatabase db = this.getReadableDatabase();
       // Cursor cur =db.rawQuery("select * from " +Table_Name , null);
       // cur.moveToFirst();

        if(position == 0){
            Cursor cur =db.rawQuery("select * from " +Table_Name , null);
            cur.moveToFirst();
            result_Status = cur.getString(0);
            cur.moveToFirst();
            result = result_Status;
        }
        if(position == 1){
            Cursor cur =db.rawQuery("select * from " +Table_Name , null);
            cur.moveToFirst();
            result_id = cur.getString(1);
            result = result_id.toString();
        }
        return result;
    }

    public Boolean saveData(String STATUS,String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(status,STATUS);
        values.put(id,ID);

        dis = db.insert(Table_Name,null,values);

        if(dis == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    public boolean check(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur =db.rawQuery("select * from " +Table_Name , null);
        cur.moveToFirst();

        if(cur.getCount() == 0){
            return false;
        }else{
            return true;
        }

    }
    public void deleteTable(String status,String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_Name,"STATUS=? and ID=?",new String[]{status,id});
    }
}
