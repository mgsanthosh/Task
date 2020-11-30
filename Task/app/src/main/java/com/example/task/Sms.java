package com.example.task;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class Sms extends ContactParents {

    EditText mobileno,message,stuid;
    Button sendsms,check;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        myDb = new DatabaseHelper(this);
        mobileno=(EditText)findViewById(R.id.editText1);
        message=(EditText)findViewById(R.id.editText2);
        stuid = (EditText)findViewById(R.id.stuid);
        sendsms=(Button)findViewById(R.id.button1);
        check =(Button) findViewById(R.id.check);

        check.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                String id1=stuid.getText().toString();
                if(TextUtils.isEmpty(id1)){
                    Toast.makeText(Sms.this,"Enter the Student Id",Toast.LENGTH_LONG).show();
                }
                viewme();
            }
        });

        //Performing action on button click
        sendsms.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String no=mobileno.getText().toString();
                String msg=message.getText().toString();

                if (TextUtils.isEmpty(no) || TextUtils.isEmpty(msg)) {
                    Toast.makeText(Sms.this,"Enter the Number or Fill the Message",Toast.LENGTH_LONG).show();
                }

                //Getting intent and PendingIntent instance
                Intent intent=new Intent(getApplicationContext(),Sms.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                //Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(no, null, msg, pi,null);

                Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         //Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.activity_pull_drawer, menu);
      return true;
    }

    public void viewme(){
        mobileno.setVisibility(View.VISIBLE);
        message.setVisibility(View.VISIBLE);
        sendsms.setVisibility(View.VISIBLE);
        Cursor cursor =myDb.getPhoneNumber1(stuid.getText().toString());
        if(cursor.getCount()!=0){

            while (cursor.moveToNext()){
                mobileno.setText(cursor.getString(6));
            }
        }
    }


}
