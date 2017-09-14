package com.student.john.taskmanagerclient.models.taskFields.dueDate.dueDateTextOptions;

import com.student.john.taskmanagerclient.models.Model;
import com.student.john.taskmanagerclient.models.taskFields.dueDate.DueDateText;


public class Within30Days extends DueDateText {
    public Within30Days() {
        setDescription(Model.DD_WITHIN_30_DAYS);
    }
}
