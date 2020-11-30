package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterAttendance extends AppCompatActivity {
    DatabaseHelper myDb;
    Button update;
    EditText id,atten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_attendance);
        myDb = new DatabaseHelper(this);
        update =(Button) findViewById(R.id.update);
        id = (EditText)findViewById(R.id.roll);
        atten = (EditText) findViewById(R.id.atten);

    }


    }
