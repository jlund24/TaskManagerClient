package com.student.john.taskmanagerclient.database;


import android.database.Cursor;
import android.database.CursorWrapper;

import com.student.john.taskmanagerclient.models.Model;
import com.student.john.taskmanagerclient.database.TaskDBSchema.TaskTable;
import com.student.john.taskmanagerclient.models.Task;

import java.util.HashMap;
import java.util.Map;

public class TaskCursorWrapper extends CursorWrapper {

    public TaskCursorWrapper (Cursor cursor)
    {
        super(cursor);
    }

    public Task getTask()
    {
        String taskID = getString(getColumnIndex(TaskTable.Cols.TASKID));
        String title = getString(getColumnIndex(TaskTable.Cols.TITLE));
        String dueDate = getString( getColumnIndex(TaskTable.Cols.DUEDATE) );
        String dueTime = getString(getColumnIndex(TaskTable.Cols.DUETIME));
        String priority = getString(getColumnIndex(TaskTable.Cols.PRIORITY));
        Double duration = getDouble(getColumnIndex(TaskTable.Cols.DURATION));
        String timePreference = getString(getColumnIndex(TaskTable.Cols.TIMEPREFERENCE));
        Boolean completed = getInt(getColumnIndex(TaskTable.Cols.COMPLETED)) == 1;

        Map<String, Object> params = new HashMap<>();
        if (dueDate != null && !dueDate.equals("")) params.put(Model.DUE_DATE_KEY, dueDate);
        if (dueTime != null) params.put(Model.DUE_TIME_KEY, dueTime);
        if (priority != null) params.put(Model.PRIORITY_KEY, priority);
        if (duration > 0) params.put(Model.DURATION_KEY, duration);
        if (timePreference != null) params.put(Model.TIME_PREFERENCE_KEY, duration);
        params.put(Model.COMPLETED_KEY, completed);

        return new Task (taskID, title, params);
    }
}
