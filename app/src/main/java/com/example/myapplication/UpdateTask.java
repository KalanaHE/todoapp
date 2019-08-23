package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateTask extends AppCompatActivity {
    private EditText et_utask, et_udate;
    private Button btn_back, btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        final Database db = new Database(getBaseContext());
        final Tasks tasks = (Tasks)getIntent().getSerializableExtra("tasks");
        et_utask = (EditText)findViewById(R.id.et_utask);

        et_udate = (EditText)findViewById(R.id.et_udate);
        this.btn_back = (Button)findViewById(R.id.btn_back);
        btn_update = (Button)findViewById(R.id.btn_update);

        et_utask.setText(tasks.getTaskName());

        et_udate.setText(tasks.getDate());
        et_utask.setSelection(et_utask.getText().length());

        et_udate.setSelection(et_udate.getText().length());

        this.btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateTask.this, TaskDetails.class);
                intent.putExtra("tasks", tasks);
                startActivity(intent);
            }
        });

       btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = et_utask.getText().toString();

                String address = et_udate.getText().toString();

                tasks.setTask(firstname);

                tasks.setDate(address);


                if(db.Update(tasks)){
                    //Toast.makeText(UpdateTask.this, "SUCCESS!", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("System");
                    builder.setMessage("SUCCESSFULLY UPDATED");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(UpdateTask.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.create().show();
                }else{
                    Toast.makeText(UpdateTask.this, "FAILED!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
