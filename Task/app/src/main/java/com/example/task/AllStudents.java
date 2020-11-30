package com.example.task;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AllStudents extends AppCompatActivity {
    Button viewAll1;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);
        viewAll1= findViewById(R.id.viewAll);
        myDb = new DatabaseHelper(this);
        viewAll2();
    }

    public void viewAll2() {
        viewAll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("ERROR", "NOTHING IS FOUND");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID:" + res.getString(0) + "\n");
                    buffer.append("Roll_number :"+res.getString(1)+"\n");
                    buffer.append("Student Name :" + res.getString(2) + "\n");
                    buffer.append("date of birth :" + res.getString(3) + "\n");

                    buffer.append("address :" + res.getString(4) + "\n");
                    buffer.append("standard :" + res.getString(5) + "\n");
                    buffer.append("phone number :" + res.getString(6) + "\n");
                    buffer.append("attendance  :" + res.getString(7) + "% \n\n");
                }
                //to show all the data
                showMessage("Data", buffer.toString());
            }
        });
    }
    public void showMessage(String title , String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}