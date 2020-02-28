package com.example.jobs.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBtask extends SQLiteOpenHelper {
    private static final int TABLE_VERSION=3;
    private static final String DATABASE_TNAME="tasks";
    public static final String TABLE_NAME="_table";
    public static final String Task_ID="id";
    public static final String tasble_ID="table_id";
    public static final String Task_NAME="Tname";
    public static final String START_TDATE="TstartDate";
    public static final String END_TDATE="TendDAte";
    public static final String TSPINNER="Tspin";

    public DBtask(@Nullable Context context) {
        super(context, DATABASE_TNAME, null, TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+TABLE_NAME+" ( "+
                Task_ID+" integer primary key autoincrement, "+
                tasble_ID+" text, "+
                Task_NAME+" text, "+START_TDATE+" text, "+END_TDATE+" text, "+
                TSPINNER+" text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);

    }
}
