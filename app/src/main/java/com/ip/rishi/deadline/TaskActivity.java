package com.ip.rishi.deadline;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ip.rishi.deadline.db.TaskContract;
import com.ip.rishi.deadline.db.TaskDBHelper;

public class TaskActivity extends AppCompatActivity {

    private TaskDBHelper taskDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }
    public void addTask (View view)
    {
        EditText inputField = (EditText) findViewById(R.id.taskText);
        String task = inputField.getText().toString();
        taskDBHelper = new TaskDBHelper(TaskActivity.this);
        SQLiteDatabase sqLiteDatabase = taskDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.clear();
        values.put(TaskContract.Columns.TASK, task);
        sqLiteDatabase.insertWithOnConflict(TaskContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
    public void cancelTask(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
