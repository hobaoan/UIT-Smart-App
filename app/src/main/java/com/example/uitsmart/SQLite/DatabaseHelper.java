package com.example.uitsmart.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DBName = "Weather Asset";
    public static final int DBVersion = 1;

    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    public void QueryData (String sqlData) {
        SQLiteDatabase getData = getWritableDatabase();
        getData.execSQL(sqlData);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE WEATHERASSETCURRENT(temperature char, humidity integer, wind char, time char )";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS WEATHERASSETCURRENT";
        db.execSQL(sql);
        onCreate(db);
    }
}
