package com.student.john.taskmanagerclient;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.student.john.taskmanagerclient.models.Model;
import com.student.john.taskmanagerclient.models.Task;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    EditText titleEditText;
    Spinner dueDateSpinner;
    ImageButton dueDateClearButton;
    RadioGroup dueTimeRadioGroup;
    ImageButton dueTimeClearButton;
    Spinner durationSpinner;
    ImageButton durationClearButton;
    RadioGroup priorityRadioGroup;
    ImageButton priorityClearButton;
    RadioGroup timePreferenceRadioGroup;
    ImageButton timePreferenceClearButton;
    Button cancelButton;
    Button saveButton;

    Drawable clear_icon;

    Model model = Model.getInstance();

    Task editingTask;

    private static final String EXTRA_TASK_ID_TO_EDIT = "com.student.john.taskmanagerclient.main2activity.task_id_to_edit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setUpFields();

        String editingTaskID = getIntent().getStringExtra(EXTRA_TASK_ID_TO_EDIT);
        if (editingTaskID != null)
        {
            editingTask = model.getTask(editingTaskID);
            setFieldsForEditing();
        }
    }



    private void setUpFields()
    {
        titleEditText = (EditText)findViewById(R.id.addEditTask1_title_editTextView);

        clear_icon = new IconDrawable(this, Iconify.IconValue.fa_trash_o).
                colorRes(R.color.red).sizeDp(22);

        cancelButton = (Button) findViewById(R.id.addEditTask1_cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        saveButton = (Button) findViewById(R.id.addEditTask1_saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleEditText.getText().toString().equals(""))
                {
                    Toast.makeText(Main2Activity.this, Model.NO_TITLE_ERROR, Toast.LENGTH_SHORT)
                            .show();
                }
                else if (dueTimeRadioGroup.getCheckedRadioButtonId() != -1 &&
                        dueDateSpinner.getSelectedItemPosition() == 0)
                {
                    Toast.makeText(Main2Activity.this, Model.TIME_WITHOUT_DATE_ERROR, Toast.LENGTH_SHORT)
                            .show();
                }
                else
                {
                    saveTask();
                    Intent i = new Intent(Main2Activity.this, MainActivity.class);
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
        //setUpTimePreferenceFields();


    }

    private void setFieldsForEditing()
    {
        titleEditText.setText(editingTask.getTitle());

        if (editingTask.getDueDate() != null)
        {
            dueDateSpinner.setSelection( model.getDueDateSpinnerPosition(editingTask.getDueDateString()) );
        }

        if (editingTask.getDueTime() != null)
        {
            int buttonID = model.getReverseDueTimeButtonMapping().get(editingTask.getDueTime());
            dueTimeRadioGroup.check( buttonID );
        }

        if (editingTask.getDurationString() != null)
        {
            durationSpinner.setSelection( model.getDurationSpinnerPosition(editingTask.getDurationString()));
        }

        if (editingTask.getPriority() != null)
        {
            int buttonID = model.getReversePriorityButtonMapping().get(editingTask.getPriority());
            priorityRadioGroup.check( buttonID );
        }

//        if (editingTask.getTimePreference() != null)
//        {
//            int buttonID = model.getReverseTimePreferenceButtonMapping().get(editingTask.getTimePreference());
//            timePreferenceRadioGroup.check( buttonID );
//        }
    }

    private void setUpDueDateFields()
    {
        dueDateSpinner = (Spinner) findViewById(R.id.addEditTask1_dueDate_spinner);
        dueDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                {
                    dueDateClearButton.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        dueDateClearButton = (ImageButton) findViewById(R.id.addEditTask1_dueDate_clear);
        dueDateClearButton.setImageDrawable(clear_icon);
        dueDateClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dueDateSpinner.setSelection(0);
                dueDateClearButton.setVisibility(View.GONE);
            }
        });
    }

    private void setUpDueTimeFields()
    {
        dueTimeRadioGroup = (RadioGroup) findViewById(R.id.addEditTask1_dueTime_radioGroup);
        dueTimeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                dueTimeClearButton.setVisibility(View.VISIBLE);
            }
        });
        dueTimeClearButton = (ImageButton) findViewById(R.id.addEditTask1_dueTime_clear);
        dueTimeClearButton.setImageDrawable(clear_icon);
        dueTimeClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dueTimeRadioGroup.clearCheck();
                dueTimeClearButton.setVisibility(View.GONE);
            }
        });
    }

    private void setUpDurationFields()
    {
        durationSpinner = (Spinner) findViewById(R.id.addEditTask1_duration_spinner);
        durationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                {
                    //if it's the last option, it's the custom date one
                    if (position == getResources().getStringArray(R.array.dueDateOptions).length - 1)
                    {
                        //open a time picker
                    }
                    durationClearButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        durationClearButton = (ImageButton) findViewById(R.id.addEditTask1_duration_clear);
        durationClearButton.setImageDrawable(clear_icon);
        durationClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                durationSpinner.setSelection(0);
                durationClearButton.setVisibility(View.GONE);
            }
        });
    }

    private void setUpPriorityFields()
    {
        priorityRadioGroup = (RadioGroup) findViewById(R.id.addEditTask1_priority_radioGroup);
        priorityRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                priorityClearButton.setVisibility(View.VISIBLE);
            }
        });

        priorityClearButton = (ImageButton) findViewById(R.id.addEditTask1_priority_clear);
        priorityClearButton.setImageDrawable(clear_icon);
        priorityClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                priorityRadioGroup.clearCheck();
                priorityClearButton.setVisibility(View.GONE);
            }
        });
    }

//    private void setUpTimePreferenceFields()
//    {
//        timePreferenceRadioGroup = (RadioGroup) findViewById(R.id.addEditTask1_timePreference_radioGroup);
//        timePreferenceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                timePreferenceClearButton.setVisibility(View.VISIBLE);
//            }
//        });
//        timePreferenceClearButton = (ImageButton) findViewById(R.id.addEditTask1_timePreference_clear);
//        timePreferenceClearButton.setImageDrawable(clear_icon);
//        timePreferenceClearButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timePreferenceRadioGroup.clearCheck();
//                timePreferenceClearButton.setVisibility(View.GONE);
//            }
//        });
//    }

    private void saveTask()
    {
        //set parameters for new task
        String title = titleEditText.getText().toString();

        Map<String, String> params = new HashMap<>();
        if (dueDateSpinner.getSelectedItemPosition() != 0)
        {
            params.put(Model.DUE_DATE_KEY, getDueDateSelection());

            //I nested this because there should only be a dueTime if there's a dueDate
            if (dueTimeRadioGroup.getCheckedRadioButtonId() != -1)
            {
                params.put(Model.DUE_TIME_KEY, getDueTimeSelection());
            }
        }
        if (durationSpinner.getSelectedItemPosition() != 0)
        {
            params.put(Model.DURATION_KEY, getDurationSelection());
        }
        if (priorityRadioGroup.getCheckedRadioButtonId() != -1)
        {
            params.put(Model.PRIORITY_KEY, getPrioritySelection());
        }
//        if (timePreferenceRadioGroup.getCheckedRadioButtonId() != -1)
//        {
//            params.put(Model.TIME_PREFERENCE_KEY, getTimePreferenceSelection());
//        }

        if (editingTask != null)
        {
            params.put(Model.TASK_ID_KEY, editingTask.getTaskID());
            model.updateTask(new Task(title, params));
        }
        else
        {
            //model.updateTask(new Task(title, params));
            model.addTask(new Task(title, params));
        }


    }

    public static Intent newIntent(Context packageContext, String taskID)
    {
        Intent i = new Intent(packageContext, Main2Activity.class);
        i.putExtra(EXTRA_TASK_ID_TO_EDIT, taskID);
        return i;
    }

    private String getDueDateSelection()
    {
        return getResources().getStringArray( R.array.dueDateOptions )[ dueDateSpinner.getSelectedItemPosition()];
    }

    private String getDueTimeSelection()
    {
        return model.getDueTimeButtonMapping().get( dueTimeRadioGroup.getCheckedRadioButtonId() );
    }

    private String getDurationSelection()
    {
        return getResources().getStringArray( R.array.durationOptions )[ durationSpinner.getSelectedItemPosition()];
    }

    private String getPrioritySelection()
    {
        return model.getPriorityButtonMapping().get( priorityRadioGroup.getCheckedRadioButtonId() );
    }

//    private String getTimePreferenceSelection()
//    {
//        return model.getTimePreferenceButtonMapping().get( timePreferenceRadioGroup.getCheckedRadioButtonId());
//    }



}
