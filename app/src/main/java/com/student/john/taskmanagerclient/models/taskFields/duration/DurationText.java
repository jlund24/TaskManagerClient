package com.student.john.taskmanagerclient.models.taskFields.duration;


public abstract class DurationText implements IDurationText {

    private String description;

    public String getDescription()
    {
        return this.description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }
}
