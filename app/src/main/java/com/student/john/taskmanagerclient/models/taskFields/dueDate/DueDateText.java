package com.student.john.taskmanagerclient.models.taskFields.dueDate;


public abstract class DueDateText implements IDueDateText {

    private String description;

    public String getDescription()
    {
        return description;
    }

    protected void setDescription(String description)
    {
        this.description = description;
    }
}
