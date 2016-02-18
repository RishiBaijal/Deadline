package com.ip.rishi.deadline;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.ip.rishi.deadline.db.TaskContract;
import com.ip.rishi.deadline.db.TaskDBHelper;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

    private TaskDBHelper taskDBHelper;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private ListView listView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateUI();

    }

    private void updateUI() {
        taskDBHelper = new TaskDBHelper(MainActivity.this);
        SQLiteDatabase sqlDatabase = taskDBHelper.getReadableDatabase();
        Cursor cursor = sqlDatabase.query(TaskContract.TABLE, new String[]{TaskContract.Columns.ID, TaskContract.Columns.TASK}, null, null, null, null, null);
        listAdapter = new SimpleCursorAdapter(this, R.layout.task_view, cursor, new String[]{TaskContract.Columns.TASK}, new int[]{R.id.taskTextView, 0});
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listAdapter);
    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();
        String sql = String.format("DELETE FROM %s where %s = '%s'", TaskContract.TABLE, TaskContract.Columns.TASK, task);
        taskDBHelper = new TaskDBHelper(MainActivity.this);
        SQLiteDatabase sqlDatabase = taskDBHelper.getWritableDatabase();
        sqlDatabase.execSQL(sql);
        updateUI();
    }
//    public void setDate(View view) {
//        showDialog(999);
//        Toast.makeText(getApplicationContext(), "Date set!", Toast.LENGTH_LONG).show();
//    }
//
//    protected Dialog onCreateDialog(int id) {
//        if (id == 999) {
//            return new DatePickerDialog(this, dateSetListener, year, month, day);
//        }
//        return null;
//    }
//
//    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//        public void onDateSet(DatePicker dp, int year, int month, int day) {
//            showDate(year, month + 1, day);
//        }
//
//    };
//
//    private void showDate(int year, int month, int day) {
//        dateView.setText(new StringBuilder().append(day).append("/")
//                .append(month).append("/").append(year));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void addTask(View view)
    {
//        int id = item.getItemId();
//        switch (id)
        {
        //    case R.id.action_add_task:
                Log.d("MainActivity", "New task has been added");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Add a task");
                builder.setMessage("Please add your task here:");
                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Log.d("MainActivity", inputField.getText().toString());
                        String task = inputField.getText().toString();

                        taskDBHelper = new TaskDBHelper(MainActivity.this);
                        SQLiteDatabase sqLiteDatabase = taskDBHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.clear();
                        values.put(TaskContract.Columns.TASK, task);
                        sqLiteDatabase.insertWithOnConflict(TaskContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                        updateUI();

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
//                return true;
//            default:
//                return false;
        }


    }
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_add_task:
                Log.d("MainActivity", "New task has been added");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Add a task");
                builder.setMessage("Please add your task here:");
                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Log.d("MainActivity", inputField.getText().toString());
                        String task = inputField.getText().toString();

                        taskDBHelper = new TaskDBHelper(MainActivity.this);
                        SQLiteDatabase sqLiteDatabase = taskDBHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.clear();
                        values.put(TaskContract.Columns.TASK, task);
                        sqLiteDatabase.insertWithOnConflict(TaskContract.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                        updateUI();

                    }
                });
                builder.setNegativeButton("Cancel", null);
                builder.create().show();
                return true;
            default:
                return false;
        }


    }

}
