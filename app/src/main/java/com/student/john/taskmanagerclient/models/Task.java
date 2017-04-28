package com.student.john.taskmanagerclient.models;


import com.student.john.taskmanagerclient.Model;

import java.util.Map;
import java.util.UUID;

public class Task {

    private String title;
    private String user;
    private String priority;
    //TODO make custom dateTime class or use java's latest
    private String dueDateString;
    private CustomDate dueDate;
    private String dueTime;
    private String durationString;
    private double duration;
    //TODO make enums for below
    private int intensity;
    private String timePreference;
    private int progress;
    private int type;
    private String taskID;



    public Task(String title, Map<String, String> params)
    {
        this.title = title;

        for (String key: params.keySet())
        {
            switch(key)
            {
                case Model.DUE_DATE_KEY:
                    if (!params.get(key).equals(Model.SELECT_OPTION))
                    {
                        this.dueDateString = params.get(key);
                        this.dueDate = new CustomDate(params.get(key));
                        if (this.dueTime == null)
                        {
                            this.dueTime = Model.TIME_NIGHT;
                        }
                    }

                    break;
                case Model.DUE_TIME_KEY:
                    this.dueTime = params.get(key);
                    break;
                case Model.DURATION_KEY:
                    if (!params.get(key).equals(Model.SELECT_OPTION))
                    {
                        this.durationString = params.get(key);
                        duration = Model.getInstance().getDurationValues().get(durationString);
                    }
                    break;
                case Model.PRIORITY_KEY:
                    this.priority = params.get(key);
                    break;
                case Model.TIME_PREFERENCE_KEY:
                    this.timePreference = params.get(key);
                    break;
                case Model.TASK_ID_KEY:
                    this.taskID = params.get(key);
                default:
                    break;
            }
        }
        if (this.taskID == null)
        {
            this.taskID = UUID.randomUUID().toString();
        }
    }

    public Task(String title)
    {
        this.title = title;
        this.taskID = UUID.randomUUID().toString();
    }

    private void setDurationFromString(String key)
    {
        switch(key)
        {
            case Model.DUR_30_MIN_KEY:
                duration = Model.DUR_30_MIN_VALUE;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDateString() {
        updateDueDateString();
        return dueDateString;
    }

    public void setDueDate(String dueDate) {
        this.dueDateString = dueDate;
    }

    public String getDueTime() {
        return dueTime;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString = durationString;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String getTimePreference() {
        return timePreference;
    }

    public void setTimePreference(String timePreference) {
        this.timePreference = timePreference;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public double getDuration() {
        return duration;
    }

    public CustomDate getDueDate() {
        return dueDate;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;

        if (obj == this) return true;

        if (obj.getClass() != this.getClass()) return false;

        Task task1 = (Task)obj;

        if (task1.getTaskID().equals(this.getTaskID())) return true;
        else return false;

    }

    private void updateDueDateString()
    {
        if (dueDateString != null)
        {
            dueDateString = dueDate.getCurrentDescription();
        }
    }
}
