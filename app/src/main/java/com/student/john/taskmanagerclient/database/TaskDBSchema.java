package com.student.john.taskmanagerclient.database;


public class TaskDBSchema {

    public static final class TaskTable {
        public static final String NAME = "Tasks";

        public static final class Cols {
            public static final String TASKID = "TaskID";
            public static final String TITLE = "Title";
            public static final String DUEDATE = "DueDate";
            public static final String DUETIME = "DueTime";
            public static final String PRIORITY = "Priority";
            public static final String DURATION = "Duration";
            public static final String TIMEPREFERENCE = "TimePreference";
            public static final String COMPLETED = "Completed";
        }
    }
}
