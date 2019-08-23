package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.Tasks;


import java.util.List;

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
        TextView tv_name = (TextView)view.findViewById(R.id.tv_name);
        TextView tv_address = (TextView)view.findViewById(R.id.tv_address);
        tv_name.setText("Name: " + tasks.get(position).getTaskName());
        tv_address.setText("Address: " + tasks.get(position).getDate());

        return view;
    }


}
