package com.example.myapplication.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.Tasks;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TaskListAdapter extends ArrayAdapter<Tasks> {

    private Context context;
    private List<Tasks> tasks;

    public TaskListAdapter(Context context, List<Tasks> tasks){
        super(context, R.layout.task_list, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view  = layoutInflater.inflate(R.layout.task_list, parent, false);
        TextView tv_name = (TextView)view.findViewById(R.id.tv_task);
        TextView tv_date = (TextView)view.findViewById(R.id.tv_date);
        tv_name.setText("Task: " + tasks.get(position).getTaskName());
        tv_date.setText("Date: " + tasks.get(position).getDate());


        String currentdate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        if (getItem(position).getDate().compareTo(currentdate)<0||getItem(position).getDate().equals(currentdate))
            view.setBackgroundColor(Color.RED);


        return view;
    }


}
