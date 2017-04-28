package com.student.john.taskmanagerclient;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.student.john.taskmanagerclient.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddEditTaskActivity extends AppCompatActivity {


    private EditText titleEditText;
    private ToggleButton dueDateToggleButton1;
    private ToggleButton dueDateToggleButton2;
    private Spinner dueDateSpinner;
    private ToggleButton dueTimeToggleButton1;
    private ToggleButton dueTimeToggleButton2;
    private ToggleButton dueTimeToggleButton3;
    private ToggleButton dueTimeToggleButton4;
    private ToggleButton durationToggleButton1;
    private ToggleButton durationToggleButton2;
    private Spinner durationSpinner;
    private ToggleButton priorityToggleButton1;
    private ToggleButton priorityToggleButton2;
    private ToggleButton priorityToggleButton3;
    private ToggleButton timePreferenceToggleButton1;
    private ToggleButton timePreferenceToggleButton2;
    private ToggleButton timePreferenceToggleButton3;
    private ToggleButton timePreferenceToggleButton4;

    private RadioGroup dueDateRadioGroup;
    private RadioButton dueDateRadioButton1;
    private RadioButton dueDateRadioButton2;
    private RadioButton dueDateRadioButton3;
    private RadioButton dueDateRadioButton4;
    private RadioButton dueDateRadioButton5;
    private RadioButton dueDateRadioButton6;
    private RadioButton dueDateRadioButton7;
    private RadioButton dueDateRadioButton8;
    private RadioButton dueDateRadioButton9;
    private RadioButton dueDateRadioButton10;

    private Button cancelButton;
    private Button saveButton;

    private Model model = Model.getInstance();

    private String dueDateSelection = null;
    private String dueTimeSelection = null;
    private String durationSelection = null;
    private String prioritySelection = null;
    private String timePreferenceSelection = null;

    private Task editingTask;


    private final String NO_TITLE_ERROR = "Task must have a titleEditText";
    private final String TIME_WITHOUT_DATE_ERROR = "Task has due time without a due date";

    private static final String EXTRA_TASK_ID_TO_EDIT = "com.student.john.taskmanagerclient.task_id_to_edit";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        setUpFields();
        String editingTaskID = getIntent().getStringExtra(EXTRA_TASK_ID_TO_EDIT);
        if (getIntent().getStringExtra(EXTRA_TASK_ID_TO_EDIT) != null)
        {
            editingTask = model.getTaskMap().get(editingTaskID);
            setFieldsForEditing(editingTask);
        }

    }

    private void setFieldsForEditing(Task task)
    {
        titleEditText.setText(task.getTitle());

        setDueDateFieldsForEdit(task);
        setDueTimeFieldsForEdit(task);
        setDurationFieldsForEdit(task);
        setPriorityFieldsForEdit(task);
        setTimePreferenceFieldsForEdit(task);

    }

    //this function just calls all the other functions needed to set up the views for the
    // AddEditTaskActivity
    private void setUpFields()
    {
        //set titleEditText
        titleEditText = (EditText)findViewById(R.id.addEditTask_title_editTextView);
        cancelButton = (Button) findViewById(R.id.addEditTask_cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        saveButton = (Button) findViewById(R.id.addEditTask_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleEditText.getText().toString().equals(""))
                {
                    Toast.makeText(AddEditTaskActivity.this, NO_TITLE_ERROR, Toast.LENGTH_SHORT)
                            .show();
                }
                else if (dueTimeSelection != null && dueDateSelection == null)
                {
                    Toast.makeText(AddEditTaskActivity.this, TIME_WITHOUT_DATE_ERROR, Toast.LENGTH_SHORT)
                            .show();
                }
                else
                {
                    saveTask();
                    Intent i = new Intent(AddEditTaskActivity.this, MainActivity.class);
                    setResult(RESULT_OK, i);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        });

        setUpDueDateFields();
        setUpDueTimeFields();
        setUpDurationFields();
        setUpPriorityFields();
        setUpTimePreferenceFields();
    }



    //************************************* DUE DATE FIELDS ****************************************
    /*
    initializes buttons and sets them to their initial value: none if adding a task, or with preset
    values if we're editing
     */
    private void setUpDueDateFields()
    {
//        dueDateRadioGroup = (RadioGroup) findViewById(R.id.addEditTask_dueDate_radioGroup);
//
//        //initialize radio buttons
//        List<RadioButton> dueDateRadioButtons = new ArrayList<>();
//        dueDateRadioButton1 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton1);
//        dueDateRadioButtons.add(dueDateRadioButton1);
//        dueDateRadioButton2 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton2);
//        dueDateRadioButtons.add(dueDateRadioButton2);
//        dueDateRadioButton3 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton3);
//        dueDateRadioButtons.add(dueDateRadioButton3);
//        dueDateRadioButton4 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton4);
//        dueDateRadioButtons.add(dueDateRadioButton4);
//        dueDateRadioButton5 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton5);
//        dueDateRadioButtons.add(dueDateRadioButton5);
//        dueDateRadioButton6 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton6);
//        dueDateRadioButtons.add(dueDateRadioButton6);
//        dueDateRadioButton7 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton7);
//        dueDateRadioButtons.add(dueDateRadioButton7);
//        dueDateRadioButton8 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton8);
//        dueDateRadioButtons.add(dueDateRadioButton8);
//        dueDateRadioButton9 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton9);
//        dueDateRadioButtons.add(dueDateRadioButton9);
//        dueDateRadioButton10 = (RadioButton) findViewById(R.id.addEditTask_dueDate_radioButton10);
//        dueDateRadioButtons.add(dueDateRadioButton10);
//
//        Map<Integer, String> dDButtonMap = model.getDueDateButtonMapping();
//        //set text for each button
//        for (RadioButton button : dueDateRadioButtons)
//        {
//            button.setText(dDButtonMap.get(button.getId()));
//        }





        //initialize button
        dueDateToggleButton1 = (ToggleButton) findViewById(R.id.addEditTask_dueDate_toggleButton1);
        dueDateToggleButton1.setText(model.getButtonMapping().get(dueDateToggleButton1.getId()));
        dueDateToggleButton1.setTextOn(model.getButtonMapping().get(dueDateToggleButton1.getId()));
        dueDateToggleButton1.setTextOff(model.getButtonMapping().get(dueDateToggleButton1.getId()));
        dueDateToggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    setDueDateSelection(dueDateToggleButton1.getId());
                    dueDateSelection = dueDateToggleButton1.getText().toString();
                } else
                {
                    dueDateSelection = null;
                }
            }
        });
        dueDateToggleButton2 = (ToggleButton) findViewById(R.id.addEditTask_dueDate_toggleButton2);
        dueDateToggleButton2.setText(model.getButtonMapping().get(dueDateToggleButton2.getId()));
        dueDateToggleButton2.setTextOn(model.getButtonMapping().get(dueDateToggleButton2.getId()));
        dueDateToggleButton2.setTextOff(model.getButtonMapping().get(dueDateToggleButton2.getId()));
        dueDateToggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    setDueDateSelection(dueDateToggleButton2.getId());
                    dueDateSelection = dueDateToggleButton2.getText().toString();

                } else
                {
                    dueDateSelection = null;
                }
            }
        });
        dueDateSpinner = (Spinner) findViewById(R.id.addEditTask_dueDate_spinner);
        dueDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                {
                    setDueDateSelection(dueDateSpinner.getId());
                    dueDateSelection = (String) dueDateSpinner.getSelectedItem();
                }
                else
                {
                    //dueDateSelection = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /*
    handles the radio button group like control of the due date options. If a toggle button or option
    besides "Other..." is selected on the spinner, the other toggle buttons or spinner is reset
     */
    private void setDueDateSelection(int id)
    {
        switch(id)
        {
            case R.id.addEditTask_dueDate_toggleButton1:
                if(dueDateToggleButton2.isChecked()) dueDateToggleButton2.setChecked(false);
                if(dueDateSpinner.getSelectedItemPosition() != 0) dueDateSpinner.setSelection(0);
                break;
            case R.id.addEditTask_dueDate_toggleButton2:
                if (dueDateToggleButton1.isChecked()) dueDateToggleButton1.setChecked(false);
                if (dueDateSpinner.getSelectedItemPosition() != 0) dueDateSpinner.setSelection(0);
                break;
            case R.id.addEditTask_dueDate_spinner:
                if (dueDateToggleButton1.isChecked()) dueDateToggleButton1.setChecked(false);
                if(dueDateToggleButton2.isChecked()) dueDateToggleButton2.setChecked(false);
                break;
            default:
                break;
        }
    }

    private void setDueDateFieldsForEdit(Task task)
    {
        if (task.getDueDate() != null)
        {
            //get string of what the current relationship between the due date and current date is
            String description = task.getDueDateString();
            //then figure out which button or spinner option that refers to and select it
            if (dueDateToggleButton1.getText().toString().equals(description))
            {
                dueDateToggleButton1.performClick();
            }
            else if (dueDateToggleButton2.getText().toString().equals(description))
            {
                dueDateToggleButton2.performClick();
            }
            else
            {
                dueDateSpinner.setSelection(model.getDueDateSpinnerPosition(description));
            }
        }
    }


    //************************************* DUE TIME FIELDS ****************************************
    /*
    initializes buttons for due time section
     */
    private void setUpDueTimeFields()
    {
        dueTimeToggleButton1 = (ToggleButton) findViewById(R.id.addEditTask_dueTime_toggleButton1);
        dueTimeToggleButton1.setText(model.getButtonMapping().get(dueTimeToggleButton1.getId()));
        dueTimeToggleButton1.setTextOn(model.getButtonMapping().get(dueTimeToggleButton1.getId()));
        dueTimeToggleButton1.setTextOff(model.getButtonMapping().get(dueTimeToggleButton1.getId()));
        dueTimeToggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDueTimeSelection(dueTimeToggleButton1.getId());
                    dueTimeSelection = dueTimeToggleButton1.getText().toString();
                }
                else {
                    dueTimeSelection = null;
                }
            }
        });

        dueTimeToggleButton2 = (ToggleButton) findViewById(R.id.addEditTask_dueTime_toggleButton2);
        dueTimeToggleButton2.setText(model.getButtonMapping().get(dueTimeToggleButton2.getId()));
        dueTimeToggleButton2.setTextOn(model.getButtonMapping().get(dueTimeToggleButton2.getId()));
        dueTimeToggleButton2.setTextOff(model.getButtonMapping().get(dueTimeToggleButton2.getId()));
        dueTimeToggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDueTimeSelection(dueTimeToggleButton2.getId());
                    dueTimeSelection = dueTimeToggleButton2.getText().toString();
                }
                else {
                    dueTimeSelection = null;
                }
            }
        });

        dueTimeToggleButton3 = (ToggleButton) findViewById(R.id.addEditTask_dueTime_toggleButton3);
        dueTimeToggleButton3.setText(model.getButtonMapping().get(dueTimeToggleButton3.getId()));
        dueTimeToggleButton3.setTextOn(model.getButtonMapping().get(dueTimeToggleButton3.getId()));
        dueTimeToggleButton3.setTextOff(model.getButtonMapping().get(dueTimeToggleButton3.getId()));
        dueTimeToggleButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDueTimeSelection(dueTimeToggleButton3.getId());
                    dueTimeSelection = dueTimeToggleButton3.getText().toString();
                }
                else {
                    dueTimeSelection = null;
                }
            }
        });

        dueTimeToggleButton4 = (ToggleButton) findViewById(R.id.addEditTask_dueTime_toggleButton4);
        dueTimeToggleButton4.setText(model.getButtonMapping().get(dueTimeToggleButton4.getId()));
        dueTimeToggleButton4.setTextOn(model.getButtonMapping().get(dueTimeToggleButton4.getId()));
        dueTimeToggleButton4.setTextOff(model.getButtonMapping().get(dueTimeToggleButton4.getId()));
        dueTimeToggleButton4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDueTimeSelection(dueTimeToggleButton4.getId());
                    dueTimeSelection = dueTimeToggleButton4.getText().toString();
                }
                else {
                    dueTimeSelection = null;
                }
            }
        });
    }


    private void setDueTimeSelection(int id)
    {
        switch(id)
        {
            case R.id.addEditTask_dueTime_toggleButton1:
                if(dueTimeToggleButton2.isChecked()) dueTimeToggleButton2.setChecked(false);
                if (dueTimeToggleButton3.isChecked()) dueTimeToggleButton3.setChecked(false);
                if (dueTimeToggleButton4.isChecked()) dueTimeToggleButton4.setChecked(false);
                break;
            case R.id.addEditTask_dueTime_toggleButton2:
                if(dueTimeToggleButton1.isChecked()) dueTimeToggleButton1.setChecked(false);
                if (dueTimeToggleButton3.isChecked()) dueTimeToggleButton3.setChecked(false);
                if (dueTimeToggleButton4.isChecked()) dueTimeToggleButton4.setChecked(false);
                break;
            case R.id.addEditTask_dueTime_toggleButton3:
                if(dueTimeToggleButton1.isChecked()) dueTimeToggleButton1.setChecked(false);
                if (dueTimeToggleButton2.isChecked()) dueTimeToggleButton2.setChecked(false);
                if (dueTimeToggleButton4.isChecked()) dueTimeToggleButton4.setChecked(false);
                break;
            case R.id.addEditTask_dueTime_toggleButton4:
                if(dueTimeToggleButton1.isChecked()) dueTimeToggleButton1.setChecked(false);
                if (dueTimeToggleButton2.isChecked()) dueTimeToggleButton2.setChecked(false);
                if (dueTimeToggleButton3.isChecked()) dueTimeToggleButton3.setChecked(false);
                break;
            default:
                break;
        }
    }

    private void setDueTimeFieldsForEdit(Task task)
    {

        switch (task.getDueTime()) {
            case Model.TIME_MORNING:
                dueTimeToggleButton1.performClick();
                break;
            case Model.TIME_AFTERNOON:
                dueTimeToggleButton2.performClick();
                break;
            case Model.TIME_EVENING:
                dueTimeToggleButton3.performClick();
                break;
            case Model.TIME_NIGHT:
                dueTimeToggleButton4.performClick();
                break;
            default:
                break;
        }

    }

    //************************************* DURATION FIELDS ****************************************
    private void setUpDurationFields()
    {
        durationToggleButton1 = (ToggleButton) findViewById(R.id.addEditTask_duration_toggleButton1);
        durationToggleButton1.setText(model.getButtonMapping().get(durationToggleButton1.getId()));
        durationToggleButton1.setTextOn(model.getButtonMapping().get(durationToggleButton1.getId()));
        durationToggleButton1.setTextOff(model.getButtonMapping().get(durationToggleButton1.getId()));
        durationToggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDurationSelection(durationToggleButton1.getId());
                    durationSelection = durationToggleButton1.getText().toString();
                } else{
                    durationSelection = null;
                }
            }
        });

        durationToggleButton2 = (ToggleButton) findViewById(R.id.addEditTask_duration_toggleButton2);
        durationToggleButton2.setText(model.getButtonMapping().get(durationToggleButton2.getId()));
        durationToggleButton2.setTextOn(model.getButtonMapping().get(durationToggleButton2.getId()));
        durationToggleButton2.setTextOff(model.getButtonMapping().get(durationToggleButton2.getId()));
        durationToggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setDurationSelection(durationToggleButton2.getId());
                    durationSelection = durationToggleButton2.getText().toString();
                } else {
                    durationSelection = null;
                }
            }
        });

        durationSpinner = (Spinner) findViewById(R.id.addEditTask_duration_spinner);
        durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                {
                    setDurationSelection(durationSpinner.getId());
                    durationSelection = (String) durationSpinner.getSelectedItem();
                } else {
                    //durationSelection = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setDurationSelection(int id)
    {
        switch(id)
        {
            case R.id.addEditTask_duration_toggleButton1:
                if(durationToggleButton2.isChecked()) durationToggleButton2.setChecked(false);
                if(durationSpinner.getSelectedItemPosition() != 0) durationSpinner.setSelection(0);
                break;
            case R.id.addEditTask_duration_toggleButton2:
                if (durationToggleButton1.isChecked()) durationToggleButton1.setChecked(false);
                if (durationSpinner.getSelectedItemPosition() != 0) durationSpinner.setSelection(0);
                break;
            case R.id.addEditTask_duration_spinner:
                if (durationToggleButton1.isChecked()) durationToggleButton1.setChecked(false);
                if(durationToggleButton2.isChecked()) durationToggleButton2.setChecked(false);
                break;
            default:
                break;
        }
    }

    private void setDurationFieldsForEdit(Task task)
    {
        if (task.getDurationString() != null)
        {
            String description = task.getDueDateString();
            //then figure out which button or spinner option that refers to and select it
            if (durationToggleButton1.getText().toString().equals(description))
            {
                durationToggleButton1.performClick();
            }
            else if (durationToggleButton2.getText().toString().equals(description))
            {
                durationToggleButton2.performClick();
            }
            else
            {
                durationSpinner.setSelection( model.getDurationSpinnerPosition(description) );
            }
        }

    }

    //************************************* PRIORITY FIELDS ****************************************
    private void setUpPriorityFields()
    {
        priorityToggleButton1 = (ToggleButton) findViewById(R.id.addEditTask_priority_toggleButton1);
        priorityToggleButton1.setText(model.getButtonMapping().get(priorityToggleButton1.getId()));
        priorityToggleButton1.setTextOn(model.getButtonMapping().get(priorityToggleButton1.getId()));
        priorityToggleButton1.setTextOff(model.getButtonMapping().get(priorityToggleButton1.getId()));
        priorityToggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setPrioritySelection(priorityToggleButton1.getId());
                    prioritySelection = priorityToggleButton1.getText().toString();
                } else {
                    prioritySelection = null;
                }
            }
        });

        priorityToggleButton2 = (ToggleButton) findViewById(R.id.addEditTask_priority_toggleButton2);
        priorityToggleButton2.setText(model.getButtonMapping().get(priorityToggleButton2.getId()));
        priorityToggleButton2.setTextOn(model.getButtonMapping().get(priorityToggleButton2.getId()));
        priorityToggleButton2.setTextOff(model.getButtonMapping().get(priorityToggleButton2.getId()));
        priorityToggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setPrioritySelection(priorityToggleButton2.getId());
                    prioritySelection = priorityToggleButton2.getText().toString();
                } else {
                    prioritySelection = null;
                }
            }
        });

        priorityToggleButton3 = (ToggleButton) findViewById(R.id.addEditTask_priority_toggleButton3);
        priorityToggleButton3.setText(model.getButtonMapping().get(priorityToggleButton3.getId()));
        priorityToggleButton3.setTextOn(model.getButtonMapping().get(priorityToggleButton3.getId()));
        priorityToggleButton3.setTextOff(model.getButtonMapping().get(priorityToggleButton3.getId()));
        priorityToggleButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setPrioritySelection(priorityToggleButton3.getId());
                    prioritySelection = priorityToggleButton3.getText().toString();
                } else {
                    prioritySelection = null;
                }
            }
        });
    }

    private void setPrioritySelection(int id)
    {
        switch(id)
        {
            case R.id.addEditTask_priority_toggleButton1:
                if(priorityToggleButton2.isChecked()) priorityToggleButton2.setChecked(false);
                if (priorityToggleButton3.isChecked()) priorityToggleButton3.setChecked(false);
                break;
            case R.id.addEditTask_priority_toggleButton2:
                if(priorityToggleButton1.isChecked()) priorityToggleButton1.setChecked(false);
                if (priorityToggleButton3.isChecked()) priorityToggleButton3.setChecked(false);
                break;
            case R.id.addEditTask_priority_toggleButton3:
                if(priorityToggleButton1.isChecked()) priorityToggleButton1.setChecked(false);
                if (priorityToggleButton2.isChecked()) priorityToggleButton2.setChecked(false);
                break;
            default:
                break;
        }
    }

    private void setPriorityFieldsForEdit(Task task)
    {

        if (task.getPriority() != null)
        {
            switch (task.getPriority()) {
                case Model.PRIORITY_LOW:
                    priorityToggleButton1.performClick();
                    break;
                case Model.PRIORITY_MED:
                    priorityToggleButton2.performClick();
                    break;
                case Model.PRIORITY_HIGH:
                    priorityToggleButton3.performClick();
                    break;
                default:
                    break;
            }
        }
    }
    //************************************* TIME PREFERENCE FIELDS *********************************
    private void setUpTimePreferenceFields()
    {
        timePreferenceToggleButton1 = (ToggleButton) findViewById(R.id.addEditTask_timePreference_toggleButton1);
        timePreferenceToggleButton1.setText(model.getButtonMapping().get(timePreferenceToggleButton1.getId()));
        timePreferenceToggleButton1.setTextOn(model.getButtonMapping().get(timePreferenceToggleButton1.getId()));
        timePreferenceToggleButton1.setTextOff(model.getButtonMapping().get(timePreferenceToggleButton1.getId()));
        timePreferenceToggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTimePreferenceSelection(timePreferenceToggleButton1.getId());
                    timePreferenceSelection = timePreferenceToggleButton1.getText().toString();
                } else {
                    timePreferenceSelection = null;
                }
            }
        });

        timePreferenceToggleButton2 = (ToggleButton) findViewById(R.id.addEditTask_timePreference_toggleButton2);
        timePreferenceToggleButton2.setText(model.getButtonMapping().get(timePreferenceToggleButton2.getId()));
        timePreferenceToggleButton2.setTextOn(model.getButtonMapping().get(timePreferenceToggleButton2.getId()));
        timePreferenceToggleButton2.setTextOff(model.getButtonMapping().get(timePreferenceToggleButton2.getId()));
        timePreferenceToggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTimePreferenceSelection(timePreferenceToggleButton2.getId());
                    timePreferenceSelection = timePreferenceToggleButton2.getText().toString();
                } else {
                    timePreferenceSelection = null;
                }
            }
        });

        timePreferenceToggleButton3 = (ToggleButton) findViewById(R.id.addEditTask_timePreference_toggleButton3);
        timePreferenceToggleButton3.setText(model.getButtonMapping().get(timePreferenceToggleButton3.getId()));
        timePreferenceToggleButton3.setTextOn(model.getButtonMapping().get(timePreferenceToggleButton3.getId()));
        timePreferenceToggleButton3.setTextOff(model.getButtonMapping().get(timePreferenceToggleButton3.getId()));
        timePreferenceToggleButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setTimePreferenceSelection(timePreferenceToggleButton3.getId());
                    timePreferenceSelection = timePreferenceToggleButton3.getText().toString();
                } else {
                    timePreferenceSelection = null;
                }
            }
        });

        timePreferenceToggleButton4 = (ToggleButton) findViewById(R.id.addEditTask_timePreference_toggleButton4);
        timePreferenceToggleButton4.setText(model.getButtonMapping().get(timePreferenceToggleButton4.getId()));
        timePreferenceToggleButton4.setTextOn(model.getButtonMapping().get(timePreferenceToggleButton4.getId()));
        timePreferenceToggleButton4.setTextOff(model.getButtonMapping().get(timePreferenceToggleButton4.getId()));
        timePreferenceToggleButton4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setTimePreferenceSelection(timePreferenceToggleButton4.getId());
                    timePreferenceSelection = timePreferenceToggleButton4.getText().toString();
                } else {
                    timePreferenceSelection = null;
                }
            }
        });
    }

    private void setTimePreferenceSelection(int id)
    {
        switch(id)
        {
            case R.id.addEditTask_timePreference_toggleButton1:
                if(timePreferenceToggleButton2.isChecked()) timePreferenceToggleButton2.setChecked(false);
                if (timePreferenceToggleButton3.isChecked()) timePreferenceToggleButton3.setChecked(false);
                if (timePreferenceToggleButton4.isChecked()) timePreferenceToggleButton4.setChecked(false);
                break;
            case R.id.addEditTask_timePreference_toggleButton2:
                if (timePreferenceToggleButton1.isChecked()) timePreferenceToggleButton1.setChecked(false);
                if (timePreferenceToggleButton3.isChecked()) timePreferenceToggleButton3.setChecked(false);
                if (timePreferenceToggleButton4.isChecked()) timePreferenceToggleButton4.setChecked(false);
                break;
            case R.id.addEditTask_timePreference_toggleButton3:
                if(timePreferenceToggleButton1.isChecked()) timePreferenceToggleButton1.setChecked(false);
                if (timePreferenceToggleButton2.isChecked()) timePreferenceToggleButton2.setChecked(false);
                if (timePreferenceToggleButton4.isChecked()) timePreferenceToggleButton4.setChecked(false);
                break;
            case R.id.addEditTask_timePreference_toggleButton4:
                if(timePreferenceToggleButton1.isChecked()) timePreferenceToggleButton1.setChecked(false);
                if (timePreferenceToggleButton2.isChecked()) timePreferenceToggleButton2.setChecked(false);
                if (timePreferenceToggleButton3.isChecked()) timePreferenceToggleButton3.setChecked(false);
                break;
            default:
                break;
        }
    }

    private void setTimePreferenceFieldsForEdit(Task task)
    {
        if (task.getTimePreference() != null)
        {
            switch (task.getTimePreference()) {
                case Model.TIME_MORNING:
                    timePreferenceToggleButton1.performClick();
                    break;
                case Model.TIME_AFTERNOON:
                    timePreferenceToggleButton2.performClick();
                    break;
                case Model.TIME_EVENING:
                    timePreferenceToggleButton3.performClick();
                    break;
                case Model.TIME_NIGHT:
                    timePreferenceToggleButton4.performClick();
                    break;
                default:
                    break;
            }
        }
    }

    private void saveTask()
    {
        //set parameters for new task
        String title = titleEditText.getText().toString();

        Map<String, String> params = new HashMap<>();
        if (dueDateSelection != null)
        {
            params.put(Model.DUE_DATE_KEY, dueDateSelection);

            //I nested this because there should only be a dueTime if there's a dueDate
            if (dueTimeSelection != null)
            {
                params.put(Model.DUE_TIME_KEY, dueTimeSelection);
            }
        }
        if (durationSelection != null)
        {
            params.put(Model.DURATION_KEY, durationSelection);
        }
        if (prioritySelection != null)
        {
            params.put(Model.PRIORITY_KEY, prioritySelection);
        }
        if (timePreferenceSelection != null)
        {
            params.put(Model.TIME_PREFERENCE_KEY, timePreferenceSelection);
        }

        if (editingTask != null)
        {
            params.put(Model.TASK_ID_KEY, editingTask.getTaskID());
        }
        model.updateTask(new Task(title, params));

    }

    public static Intent newIntent(Context packageContext, String taskID)
    {
        Intent i = new Intent(packageContext, AddEditTaskActivity.class);
        i.putExtra(EXTRA_TASK_ID_TO_EDIT, taskID);
        return i;
    }


}
