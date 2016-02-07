package com.ip.rishi.deadline.db;

/**
 * Created by Rishi on 31/01/16.
 */

import android.provider.BaseColumns;
public class TaskContract {
    public static final String DB_NAME = "com.ip.rishi.deadline.db.tasks";
    public static final int DB_VERSION = 1;
    public static final String TABLE = "tasks";

    public class Columns
    {
        public static final String TASK = "task";
        public static final String ID = BaseColumns._ID;
    }
}
