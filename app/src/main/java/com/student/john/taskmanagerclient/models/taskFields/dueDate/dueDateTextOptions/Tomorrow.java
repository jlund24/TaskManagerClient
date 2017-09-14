package com.student.john.taskmanagerclient.models.taskFields.dueDate.dueDateTextOptions;

import com.student.john.taskmanagerclient.models.taskFields.dueDate.DueDateText;

import static com.student.john.taskmanagerclient.models.Model.DD_TOMORROW;


public class Tomorrow extends DueDateText {

    public Tomorrow()
    {
        setDescription(DD_TOMORROW);
    }
}
