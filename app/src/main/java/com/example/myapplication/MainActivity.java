package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.adapters.TaskListAdapter;


import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    Database database;
    EditText et_task, et_date;
    Button btn_add, btn_view;
    ListView lv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this);

        btn_add = (Button)findViewById(R.id.btn_add);
        lv_list = (ListView)findViewById(R.id.lv_list);

        TaskListAdapter taskListAdapter = new TaskListAdapter(this, database.DisplayAll());
        taskListAdapter.sort(new Comparator<Tasks>() {
            @Override
            public int compare(Tasks lhs, Tasks rhs) {
                return lhs.getTaskName().compareTo(rhs.getTaskName());
            }
        });
        lv_list.setAdapter(taskListAdapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tasks tasks = database.DisplayAll().get(position);
                Intent intent = new Intent(MainActivity.this, TaskDetails.class);
                intent.putExtra("tasks", tasks);
                startActivity(intent);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTask.class);
                startActivity(intent);
            }
        });


    }


}
