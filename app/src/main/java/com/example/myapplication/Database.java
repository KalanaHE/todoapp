package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "tasksapp.db";
    public static final String TABLE_NAME = "tbl_task";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, taskname TEXT, taskdate date)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean InsertData(Tasks tasks){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("taskname", tasks.getTaskName());

            contentValues.put("taskdate", tasks.getDate());
            long result = db.insert(TABLE_NAME, null, contentValues);
            db.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }

    public List<Tasks> DisplayAll(){
        try{
            List<Tasks> tasks = new ArrayList<Tasks>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            if(cursor.moveToFirst()){
                do{
                    Tasks taskss = new Tasks();
                    taskss.setId(cursor.getInt(0));
                    taskss.setTask(cursor.getString(1));

                    taskss.setDate(cursor.getString(2));
                    tasks.add(taskss);
                }while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return tasks;
        }catch (Exception e){
            return null;
        }

    }

    public boolean Delete(int id){
        try{
            SQLiteDatabase db = getWritableDatabase();
            int row = db.delete(TABLE_NAME, "ID = ?", new String[]{String.valueOf(id)});
            db.close();
            return row > 0;
        }catch (Exception e){
           return false;
        }
    }

    public boolean Update(Tasks tasks){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("taskname", tasks.getTaskName());

            contentValues.put("taskdate", tasks.getDate());
            int row = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{String.valueOf(tasks.getId())});
            db.close();
            return row > 0;
        }catch (Exception e){
            return false;
        }
    }

}
