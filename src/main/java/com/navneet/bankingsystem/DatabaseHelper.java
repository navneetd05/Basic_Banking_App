package com.navneet.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 + " (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(8356783498,'Navneet',8976.00,'navneet@gmail.com','XXXXXXXXXXXX7856','ABC09876543')");
        db.execSQL("insert into user_table values(9894567342,'Rajesh',9675.67,'rajesh@gmail.com','XXXXXXXXXXXX2341','ABC98765432')");
        db.execSQL("insert into user_table values(8085067589,'Suresh',1359.56,'suresh@gmail.com','XXXXXXXXXXXX9778','CAB87654321')");
        db.execSQL("insert into user_table values(7828564378,'Gaurav',6534.01,'gaurav@gmail.com','XXXXXXXXXXXX4562','ABC76543210')");
        db.execSQL("insert into user_table values(9872156439,'Manoj',2607.48,'manoj@gmail.com','XXXXXXXXXXXX3562','BCA65432109')");
        db.execSQL("insert into user_table values(8956674369,'Anmol',9450.16,'anmol@gmail.com','XXXXXXXXXXXX9689','CAB54321098')");
        db.execSQL("insert into user_table values(9785687334,'Karan',5954.00,'karan@gmail.com','XXXXXXXXXXXX4523','ABC43210987')");
        db.execSQL("insert into user_table values(9775644789,'Amit',8570.22,'amit@gmail.com','XXXXXXXXXXXX7546','CBA32109876')");
        db.execSQL("insert into user_table values(9806674358,'Pragyan',4398.46,'pragyan@gmail.com','XXXXXXXXXXXX7421','BCA21098765')");
        db.execSQL("insert into user_table values(7845563289,'Deepak',273.90,'deepak@gmail.com','XXXXXXXXXXXX5754','ACB10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
