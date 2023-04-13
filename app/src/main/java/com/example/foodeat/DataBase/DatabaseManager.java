package com.example.foodeat.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "mydb.sqlite";
    private static final int CURRENT_DB_VERSION = 1;

    private static DatabaseManager instance;

    public static DatabaseManager getInstance(Context context){
        if(instance==null){
            instance= new DatabaseManager(context);
        }
        return instance;
    }

    private DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user "+
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , username TEXT, password TEXT, nom TEXT, prenom TEXT, email TEXT, adresse TEXT, pic TEXT);");
        db.execSQL("create table food "+
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, pic TEXT, description TEXT, fee INTEGER, numberincart FLOAT, id_category INT);");
        db.execSQL("create table category "+
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT, pic TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                //code sql 1->2
            case 2:
                //code sql 2->3
            case 3:
                //code sql 3->4
            default:
        }
    }
}
