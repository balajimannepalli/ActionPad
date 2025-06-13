package com.example.taskmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;

public class TasksListActivity extends AppCompatActivity {
    RecyclerView recycler;
    TaskAdapter taskAdapter;
    Button back,logoutb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_taskslist);
        recycler = findViewById(R.id.recycler);
        back = findViewById(R.id.back);
        logoutb=findViewById(R.id.logoutb);
        Collections.sort(TasksActivity.taskList, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDate().compareTo(t2.getDate());
            }
        });
        taskAdapter = new TaskAdapter(TasksActivity.taskList);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(taskAdapter);
        logoutb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear login session (SharedPreferences)
                SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                // Redirect to MainActivity (login screen)
                Intent intent = new Intent(TasksListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
