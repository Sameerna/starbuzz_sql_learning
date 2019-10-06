package com.example.starbuzz;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


class StarbuzzDatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "starbuzz";
    private static final int DB_VERSION = 2;


    StarbuzzDatabaseHelper(Context context){

        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
       updateMyDatabase(db,0 ,DB_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion);
    }
        private static void insertDrink (SQLiteDatabase db,String name,String description, int resourceId){

            ContentValues drinkvalues = new ContentValues();
            drinkvalues.put("NAME",name);
            drinkvalues.put("DESCRIPTION",description);
            drinkvalues.put("IMAGE_RESOURCE_ID",resourceId);
            db.insert("DRINK",null,drinkvalues);

        }
        private void updateMyDatabase(SQLiteDatabase db,int oldVersion,int newVersion){
        if(oldVersion < 1){
            db.execSQL ("CREATE TABLE DRINK(_id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    +"NAME TEXT,"
                    +"DESCRIPTION TEXT,"
                    +"IMAGE_RESOURCE_ID INTEGER);");

            insertDrink (db,"Latte","Espresso and steamed milk",R.drawable.latte);
            insertDrink(db,"Cappuccino","Espresso hot milk, cream",R.drawable.cappuccino);
            insertDrink(db,"filter","anna coffee",R.drawable.filter);
        }
        if(oldVersion < 2){
              db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");
        }
        }

    }



