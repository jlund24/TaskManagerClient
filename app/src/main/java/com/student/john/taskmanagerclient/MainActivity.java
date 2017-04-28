package com.student.john.taskmanagerclient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;
import com.student.john.taskmanagerclient.models.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addButton;
    private RecyclerView taskRecyclerView;
    private TaskAdapter adapter;

    private static final int REQUEST_CODE_SAVE = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskRecyclerView = (RecyclerView) findViewById(R.id.taskList_recyclerView);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        addButton = (FloatingActionButton) findViewById(R.id.taskList_addButton);
        addButton.setImageDrawable( new IconDrawable(MainActivity.this, Iconify.IconValue.fa_plus)
                .sizeDp(20).colorRes(R.color.white) );
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, AddEditTaskActivity.class);
//                startActivityForResult(i, REQUEST_CODE_SAVE);

                startActivity(new Intent(MainActivity.this, Main2Activity.class));

            }
        });

        updateUI();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK)
        {
            return;
        }
        else
        {
            updateUI();
        }
    }

    private void updateUI()
    {
        //get tasks
        List<Task> tasks = Model.getInstance().getTasks();

        adapter = new TaskAdapter(tasks);
        taskRecyclerView.setAdapter(adapter);


    }

    private class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title;
        public TextView dueDate;
        public ImageView priority;

        private Task task;

        public TaskHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.taskListItem_title);
            dueDate = (TextView) itemView.findViewById(R.id.taskListItem_dueDate);
            priority = (ImageView) itemView.findViewById(R.id.taskListItem_priority);
        }

        public void bindTask(Task task)
        {
            this.task = task;
            title.setText(task.getTitle());
            dueDate.setText(task.getDueDateString());
            if (task.getPriority() != null)
            {

                int color = Model.getInstance().getPriorityColors().get(task.getPriority());
                Drawable topIcon = new IconDrawable(MainActivity.this, Iconify.IconValue.fa_flag_o).
                        colorRes(color).sizeDp(15);
                priority.setImageDrawable(topIcon);
                priority.setVisibility(View.VISIBLE);
            }
        }

        public void onClick(View v)
        {
            //send taskID to fragment
            Intent i = Main2Activity.newIntent(MainActivity.this, task.getTaskID());
            startActivityForResult(i, REQUEST_CODE_SAVE);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private List<Task> tasks;

        public TaskAdapter(List<Task> tasks)
        {
            this.tasks = tasks;
        }

        @Override
        public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout.task_list_item, parent, false);
            return new TaskHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskHolder holder, int position)
        {
            Task task = tasks.get(position);
            holder.bindTask(task);
        }
        @Override
        public int getItemCount()
        {
            return tasks.size();
        }

    }



}
