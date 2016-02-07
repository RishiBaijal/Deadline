package com.ip.rishi.deadline.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class TaskDBHelper extends SQLiteOpenHelper {
    public TaskDBHelper(Context context)
    {
        super(context, TaskContract.DB_NAME, null, TaskContract.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String sqlQuery = String.format("CREATE TABLE %s (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "%s TEXT)", TaskContract.TABLE, TaskContract.Columns.TASK);
        Log.d("TaskDBHelper", "Query to form table: " + sqlQuery);
        sqLiteDatabase.execSQL(sqlQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i1, int i2)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TaskContract.TABLE);
        onCreate(sqLiteDatabase);
    }
}
