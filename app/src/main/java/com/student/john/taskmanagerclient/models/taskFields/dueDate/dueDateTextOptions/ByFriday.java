package com.student.john.taskmanagerclient.models.taskFields.dueDate.dueDateTextOptions;

import com.student.john.taskmanagerclient.models.taskFields.dueDate.DueDateText;

import static com.student.john.taskmanagerclient.models.Model.DD_BY_FRIDAY;


public class ByFriday extends DueDateText {

    public ByFriday()
    {
        setDescription(DD_BY_FRIDAY);
    }
}
