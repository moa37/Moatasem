package com.example.jobs.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    private static final int TABLE_VERSION=1;
    private static final String DATABASE_NAME="jobs";
    public static final String TABLE_NAME="_table";
    public static final String COL_ID="id";
    public static final String JOB_NAME="name";
    public static final String START_DATE="startDate";
    public static final String END_DATE="endDAte";
    public static final String SPINNER="spin";

    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+TABLE_NAME+" ( "+
                COL_ID+" integer primary key autoincrement, "+
                JOB_NAME+" text, "+END_DATE+" text, "+SPINNER+" text, "+
                START_DATE+" text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);

    }
}
