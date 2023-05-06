package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskAdapter;

public class MainActivity extends AppCompatActivity implements TaskAdapter.TaskItemListener{
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private EditText eTitle, eDesc, eDate;
    private CheckBox sttCheck;
    private Button btAdd, btUpdate;
    private int pcurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        taskAdapter = new TaskAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.setClickListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                String title = eTitle.getText().toString();
                String desc = eDesc.getText().toString();
                String date = eDate.getText().toString();
                boolean isDone = sttCheck.isChecked();
                try {
                    task.setTitle(title);
                    task.setDescription(desc);
                    task.setDate(date);
                    task.setDone(isDone);
                    taskAdapter.add(task);
                }
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_LONG).show();
                }

            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                String title = eTitle.getText().toString();
                String desc = eDesc.getText().toString();
                String date = eDate.getText().toString();
                boolean isDone = sttCheck.isChecked();
                try {
                    task.setTitle(title);
                    task.setDescription(desc);
                    task.setDate(date);
                    task.setDone(isDone);
                    taskAdapter.update(pcurrent,task);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void initView() {
        recyclerView = findViewById(R.id.recycleView);
        eTitle = findViewById(R.id.title);
        eDate = findViewById(R.id.date);
        eDesc = findViewById(R.id.description);
        sttCheck = findViewById(R.id.donecheckbox);
        btAdd = findViewById(R.id.addbtn);
        btUpdate = findViewById(R.id.updatebtn);
        btUpdate.setEnabled(false);
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurrent = position;
        Task task = taskAdapter.getItem(position);
        eTitle.setText(task.getTitle());
        eDesc.setText(task.getDescription());
        eDate.setText(task.getDate());
        sttCheck.setChecked(task.isDone());
    }
}