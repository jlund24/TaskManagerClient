package com.student.john.taskmanagerclient.models;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.util.SortedList;

import com.student.john.taskmanagerclient.R;
import com.student.john.taskmanagerclient.database.TaskBaseHelper;
import com.student.john.taskmanagerclient.database.TaskCursorWrapper;
import com.student.john.taskmanagerclient.database.TaskDBSchema.TaskTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    public static final String DUE_DATE_KEY = "DueDate";
    public static final String DUE_TIME_KEY = "DueTime";
    public static final String DURATION_KEY = "Duration";
    public static final String PRIORITY_KEY = "Priority";
    public static final String TIME_PREFERENCE_KEY = "TimePreference";
    public static final String TASK_ID_KEY = "TaskID";
    public static final String COMPLETED_KEY = "Completed";

    public static final String SELECT_OPTION = "Select option";

    public static final String DD_BY_FRIDAY = "By Friday";
    public static final String DD_TOMORROW = "Tomorrow";
    public static final String DD_TODAY = "Today";
    public static final String DD_NEXT_3_DAYS = "Next 3 days";
    public static final String DD_WITHIN_WEEK = "Within the week";
    public static final String DD_NEXT_7_DAYS = "Next 7 days";
    public static final String DD_WITHIN_2_WEEKS = "Within 2 weeks";
    public static final String DD_WITHIN_MONTH = "Within the month";
    public static final String DD_WITHIN_30_DAYS = "Within 30 days";
    public static final String DD_SPECIFIC_DATE = "Specific Date";

    public static final String PRIORITY_HIGH = "H";
    public static final String PRIORITY_MED = "M";
    public static final String PRIORITY_LOW = "L";

    public static final String TIME_MORNING = "M";
    public static final String TIME_AFTERNOON = "A";
    public static final String TIME_EVENING = "E";
    public static final String TIME_NIGHT = "N";

    public static final String DUR_30_MIN_KEY = "30 min";
    public static final String DUR_1_HR_KEY = "1 hr";
    public static final String DUR_15_MIN_KEY = "15 min";
    public static final String DUR_1_5_HR_KEY = "1.5 hr";
    public static final String DUR_2_HR_KEY = "2 hr";
    public static final String DUR_5_HR_KEY = "5 hr";

    public static final double DUR_30_MIN_VALUE = 0.5;
    public static final double DUR_1_HR_VALUE = 1.0;
    public static final double DUR_15_MIN_VALUE = 0.25;
    public static final double DUR_1_5_HR_VALUE = 1.5;
    public static final double DUR_2_HR_VALUE = 2.0;
    public static final double DUR_5_HR_VALUE = 5.0;

    public static final String NO_TITLE_ERROR = "Task must have a title";
    public static final String TIME_WITHOUT_DATE_ERROR = "Task has due time without a due date";

    private List<Task> tasks = new ArrayList<>();
    private Map<String, Task> taskMap = new HashMap<>();
    private Map<String, Integer> priorityColors;
    private Map<String, Double> durationValues;
    private Map<Double, String> reverseDurationValues;
    private ArrayList<String> dueDateOptions;
    private Map<Integer, String> dueTimeButtonMapping;
    private Map<String, Integer> reverseDueTimeButtonMapping;
    private Map<Integer, String> priorityButtonMapping;
    private Map<String, Integer> reversePriorityButtonMapping;
    private Map<Integer, String> timePreferenceButtonMapping;
    private Map<String, Integer> reverseTimePreferenceButtonMapping;
    private ArrayList<String> durationOptions;
    private ArrayList<String> timeOptions;
    private ArrayList<String> priorityOptions;

    //database stuff
    private Context context;
    private SQLiteDatabase database;

    private String user;

    public static Model instance = null;

    private Model() {
        //initialize priorityColors
        priorityColors = new HashMap<>();
        priorityColors.put(PRIORITY_HIGH, R.color.red);
        priorityColors.put(PRIORITY_MED, R.color.orange);
        priorityColors.put(PRIORITY_LOW, R.color.green);

        //initialize durationValue map
        durationValues = new HashMap<>();
        durationValues.put(DUR_30_MIN_KEY, DUR_30_MIN_VALUE);
        durationValues.put(DUR_1_HR_KEY, DUR_1_HR_VALUE);
        durationValues.put(DUR_15_MIN_KEY, DUR_15_MIN_VALUE);
        durationValues.put(DUR_1_5_HR_KEY, DUR_1_5_HR_VALUE);
        durationValues.put(DUR_2_HR_KEY, DUR_2_HR_VALUE);
        durationValues.put(DUR_5_HR_KEY, DUR_5_HR_VALUE);

        //initialize reverseDurationValues map
        reverseDurationValues = new HashMap<>();
        for (String key : durationValues.keySet())
        {
            reverseDurationValues.put(durationValues.get(key), key);
        }

        //initialize dueTimeButtonMapping
        dueTimeButtonMapping = new HashMap<>();
        dueTimeButtonMapping.put(R.id.addEditTask1_dueTime_radioButton1, TIME_MORNING);
        dueTimeButtonMapping.put(R.id.addEditTask1_dueTime_radioButton2, TIME_AFTERNOON);
        dueTimeButtonMapping.put(R.id.addEditTask1_dueTime_radioButton3, TIME_EVENING);
        dueTimeButtonMapping.put(R.id.addEditTask1_dueTime_radioButton4, TIME_NIGHT);

        //initialize reverseDueTimeButtonMapping
        reverseDueTimeButtonMapping = new HashMap<>();
        for (int key : dueTimeButtonMapping.keySet())
        {
            reverseDueTimeButtonMapping.put(dueTimeButtonMapping.get(key), key);
        }

        //initialize priorityButtonMapping
        priorityButtonMapping = new HashMap<>();
        priorityButtonMapping.put(R.id.addEditTask1_priority_radioButton1, PRIORITY_LOW);
        priorityButtonMapping.put(R.id.addEditTask1_priority_radioButton2, PRIORITY_MED);
        priorityButtonMapping.put(R.id.addEditTask1_priority_radioButton3, PRIORITY_HIGH);

        //initialize reversePriorityButtonMapping
        reversePriorityButtonMapping = new HashMap<>();
        for(int key : priorityButtonMapping.keySet())
        {
            reversePriorityButtonMapping.put(priorityButtonMapping.get(key), key);
        }

        //initialize timePreferenceButtonMapping
        timePreferenceButtonMapping = new HashMap<>();
        timePreferenceButtonMapping.put(R.id.addEditTask1_timePreference_radioButton1, TIME_MORNING);
        timePreferenceButtonMapping.put(R.id.addEditTask1_timePreference_radioButton2, TIME_AFTERNOON);
        timePreferenceButtonMapping.put(R.id.addEditTask1_timePreference_radioButton3, TIME_EVENING);
        timePreferenceButtonMapping.put(R.id.addEditTask1_timePreference_radioButton4, TIME_NIGHT);

        //initialize reverseButtonMapping
        reverseTimePreferenceButtonMapping = new HashMap<>();
        for(int key : timePreferenceButtonMapping.keySet())
        {
            reverseTimePreferenceButtonMapping.put(timePreferenceButtonMapping.get(key), key);
        }


        //initialize dueDateOptions
        //this MUST remain in the same order as the string array in strings.xml or there will be really
        //hard to debug bugs
        dueDateOptions = new ArrayList<>();
        dueDateOptions.add(SELECT_OPTION);
        dueDateOptions.add(DD_TODAY);
        dueDateOptions.add(DD_TOMORROW);
        dueDateOptions.add(DD_NEXT_3_DAYS);
        dueDateOptions.add(DD_BY_FRIDAY);
        dueDateOptions.add(DD_WITHIN_WEEK);
        dueDateOptions.add(DD_NEXT_7_DAYS);
        dueDateOptions.add(DD_WITHIN_2_WEEKS);
        dueDateOptions.add(DD_WITHIN_MONTH);
        dueDateOptions.add(DD_WITHIN_30_DAYS);

        //initialize durationOptions
        //this MUST remain in the same order as the string array in strings.xml or there will be really
        //hard to debug bugs
        durationOptions = new ArrayList<>();
        durationOptions.add(SELECT_OPTION);
        durationOptions.add(DUR_15_MIN_KEY);
        durationOptions.add(DUR_30_MIN_KEY);
        durationOptions.add(DUR_1_HR_KEY);
        durationOptions.add(DUR_1_5_HR_KEY);
        durationOptions.add(DUR_2_HR_KEY);
        durationOptions.add(DUR_5_HR_KEY);

        //initialize timeOptions
        timeOptions = new ArrayList<>();
        timeOptions.add(TIME_MORNING);
        timeOptions.add(TIME_AFTERNOON);
        timeOptions.add(TIME_EVENING);
        timeOptions.add(TIME_NIGHT);

        //initialize priorityOptions
        priorityOptions = new ArrayList<>();
        priorityOptions.add(PRIORITY_LOW);
        priorityOptions.add(PRIORITY_MED);
        priorityOptions.add(PRIORITY_HIGH);



    }

    public static Model getInstance()
    {
        if (instance == null)
        {
            instance = new Model();
        }
        return instance;
    }

    public void update()
    {

    }

    public List<Task> getTasks() {

        List<Task> tasks = new ArrayList<>();

        TaskCursorWrapper cursor = queryTasks(null, null);

        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(tasks, new Task());
        return tasks;
    }


//    public void updateTask(Task task)
//    {
//        taskMap.put(task.getTaskID(), task);
//        //remove previous version (same id)
//        tasks.remove(task);
//        //add new version
//        tasks.add(task);
//
//    }

    public void updateTask(Task task)
    {
        ContentValues values = getContentValues(task);

        database.update( TaskTable.NAME, values, TaskTable.Cols.TASKID + " = ?",
                new String[]{ task.getTaskID() } );
    }

    public void addTask(Task task)
    {
        ContentValues values = getContentValues(task);

        database.insert(TaskTable.NAME, null, values);
    }

    public Task getTask (String taskID)
    {
        TaskCursorWrapper cursor = queryTasks(TaskTable.Cols.TASKID + " = ?",
                new String[] {taskID});

        try
        {
            if (cursor.getCount() == 0)
            {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTask();
        } finally {
            cursor.close();
        }
    }

    public void deleteTask (String taskID)
    {
        database.delete(TaskTable.NAME, TaskTable.Cols.TASKID + " = ?",
                new String[]{taskID});
    }

    public Map<String, Integer> getPriorityColors() {
        return priorityColors;
    }

    public Map<String, Task> getTaskMap() {
        return taskMap;
    }

    public Map<String, Double> getDurationValues() {
        return durationValues;
    }

    public Map<Integer, String> getDueTimeButtonMapping() {
        return dueTimeButtonMapping;
    }

    public Map<Integer, String> getPriorityButtonMapping() {
        return priorityButtonMapping;
    }

    public Map<String, Integer> getReversePriorityButtonMapping() {
        return reversePriorityButtonMapping;
    }

    public Map<Integer, String> getTimePreferenceButtonMapping() {
        return timePreferenceButtonMapping;
    }

    public Map<String, Integer> getReverseTimePreferenceButtonMapping() {
        return reverseTimePreferenceButtonMapping;
    }

    public Map<String, Integer> getReverseDueTimeButtonMapping() {
        return reverseDueTimeButtonMapping;
    }

    public int getDueDateSpinnerPosition(String description)
    {
        return dueDateOptions.indexOf(description);
    }

    public int getDurationSpinnerPosition(String description)
    {
        return durationOptions.indexOf(description);
    }

    public Map<Double, String> getReverseDurationValues() {
        return reverseDurationValues;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getTimePosition(String description)
    {
        return timeOptions.indexOf(description);
    }

    public int getPriorityPosition(String description)
    {
        return priorityOptions.indexOf(description);
    }

    private static ContentValues getContentValues(Task task)
    {
        ContentValues values = new ContentValues();
        values.put(TaskTable.Cols.TASKID, task.getTaskID());
        values.put(TaskTable.Cols.TITLE, task.getTitle());
        if (task.getDueDate() != null) values.put(TaskTable.Cols.DUEDATE, task.getDueDate().getDateAsString());
        else values.put(TaskTable.Cols.DUEDATE, "");
        values.put(TaskTable.Cols.DUETIME, task.getDueTime());
        values.put(TaskTable.Cols.PRIORITY, task.getPriority());
        values.put(TaskTable.Cols.DURATION, task.getDuration());
        values.put(TaskTable.Cols.TIMEPREFERENCE, task.getTimePreference());
        values.put(TaskTable.Cols.COMPLETED, task.isCompleted() ? 1 : 0);

        return values;
    }

    private TaskCursorWrapper queryTasks (String whereClause, String[] whereArgs)
    {
        Cursor cursor = database.query(
                TaskTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new TaskCursorWrapper(cursor);
    }

    public void initializeDB(Context context)
    {
        //initialize database
        this.context = context.getApplicationContext();
        database = new TaskBaseHelper(this.context).getWritableDatabase();
    }


}
