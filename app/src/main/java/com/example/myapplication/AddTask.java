package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {
    Database database;
    EditText et_addtask, et_addate;
    Button btn_tsave, btn_tback;

    //

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);

        database = new Database(this);

        et_addtask = (EditText)findViewById(R.id.et_firstname);
        et_addate = (EditText)findViewById(R.id.et_address);
        btn_tsave = (Button)findViewById(R.id.btn_tsave);
        btn_tback = (Button)findViewById(R.id.btn_tback);

        btn_tsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskname = et_addtask.getText().toString();
                String taskdate = et_addate.getText().toString();

                if(!taskname.equals("")  || !taskdate.equals("")){

                    Tasks tasks = new Tasks();
                    tasks.setTask(taskname);
                    tasks.setDate(taskdate);

                    if (database.InsertData(tasks)) {
                        Toast.makeText(AddTask.this, "Successfully Inserted Data", Toast.LENGTH_SHORT).show();
                        et_addtask.setText("");
                        et_addate.setText("");
                    }
                }else{
                    Toast.makeText(AddTask.this, "Please complete the required field!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_tback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddTask.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


}
