package com.example.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import com.example.taskmanager.Task;

public class TasksActivity extends AppCompatActivity {
    EditText title,desc,date;
    Button addb,viewb;
    static ArrayList<Task> taskList=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tasks);
        title=findViewById(R.id.title);
        desc=findViewById(R.id.desc);
        date=findViewById(R.id.date);
        addb=findViewById(R.id.addb);
        viewb=findViewById(R.id.viewb);
        addb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tasktitle=title.getText().toString().trim();
                String taskdesc=desc.getText().toString().trim();
                String taskdate = date.getText().toString().trim();
                if (!tasktitle.isEmpty() && !taskdesc.isEmpty() && !taskdate.isEmpty()) {
                    taskList.add(new Task(tasktitle, taskdesc, taskdate));
                    Toast.makeText(TasksActivity.this, "Task Added", Toast.LENGTH_SHORT).show();
                    title.setText("");
                    desc.setText("");
                    date.setText("");
                } else {
                    Toast.makeText(TasksActivity.this, "Enter Task Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TasksActivity.this, TasksListActivity.class);
                startActivity(intent);
            }
        });

    }
}
