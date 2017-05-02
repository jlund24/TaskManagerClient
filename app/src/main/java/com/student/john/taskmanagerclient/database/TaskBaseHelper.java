package com.student.john.taskmanagerclient.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.student.john.taskmanagerclient.database.TaskDBSchema.TaskTable;

public class TaskBaseHelper extends SQLiteOpenHelper{

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "taskBase.db";

    public TaskBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TaskTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                TaskTable.Cols.TASKID + " UNIQUE, " +
                TaskTable.Cols.TITLE + ", " +
                TaskTable.Cols.DUEDATE + ", " +
                TaskTable.Cols.DUETIME + ", " +
                TaskTable.Cols.PRIORITY + ", " +
                TaskTable.Cols.DURATION + ", " +
                TaskTable.Cols.TIMEPREFERENCE + ", " +
                TaskTable.Cols.COMPLETED +
                ")";
        db.execSQL(createTableQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
