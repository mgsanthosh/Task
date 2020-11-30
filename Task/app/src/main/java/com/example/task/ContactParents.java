package com.example.task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.Toast;

public class ContactParents extends AppCompatActivity {
    DatabaseHelper myDb;
    Button check;
    EditText id,nme,ph;
    ImageView call;
    private static final int REQUEST_CALL = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_parents);
        myDb = new DatabaseHelper(this);
        check = (Button) findViewById(R.id.check);
        id = (EditText) findViewById(R.id.id);
        nme = (EditText) findViewById(R.id.nme);
        ph = (EditText) findViewById(R.id.ph);
        call = (ImageView) findViewById(R.id.call);

        phonenumber();

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //makePhone();
                String number = ph.getText().toString();
                String dial = "tel:" +number;
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(dial));

                if (ActivityCompat.checkSelfPermission(ContactParents.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }


        });








    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
    private void phonenumber() {
        check.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                nme.setVisibility(View.VISIBLE);
                ph.setVisibility(View.VISIBLE);
                call.setVisibility(View.VISIBLE);
                String id1=id.getText().toString();
                if (TextUtils.isEmpty(id1)) {
                    Toast.makeText(ContactParents.this, "Enter The Student ID ", Toast.LENGTH_LONG).show();
                } else {
                    Cursor res = myDb.getPhoneNumber(id.getText().toString());
                    if (res.getCount() == 0) {
                        showMessage("error", "no data found");
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        nme.setText(res.getString(2));
                        ph.setText(res.getString(6));
                        buffer.append("Student Name :" + res.getString(2) + "\n");
                        buffer.append("phone number :" + res.getString(6) + "\n");
                    }
                    //showMessage("Data", buffer.toString());

                }
            }


        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhone();
            }else{
                Toast.makeText(ContactParents.this, "Denied ", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void makePhone() {
        String number = ph.getText().toString();
        if(number.trim().length()>0){

            if(ContextCompat.checkSelfPermission(ContactParents.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(ContactParents.this , new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }else{
                String dial = "tel" +number;
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
            }

        }else{

        }
    }


}
