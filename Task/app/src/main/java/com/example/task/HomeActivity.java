package com.example.task;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class HomeActivity extends Glogin {
    public EditText roll_number;
    public EditText student_name;
    public EditText student_dob;
    public EditText standard;
    public EditText address,ph_no,atten;
    public Button addData,viewAll,test;
    ImageView profile;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDb = new DatabaseHelper(this);

        roll_number = (EditText) findViewById(R.id.roll);
        student_name = (EditText)findViewById(R.id.name);
        student_dob = (EditText)findViewById(R.id.dob);
        standard = (EditText)findViewById(R.id.std);
        address = (EditText)findViewById(R.id.adr);
        ph_no = (EditText)findViewById(R.id.ph_no);
        atten = (EditText)findViewById(R.id.atten);
        addData = (Button) findViewById(R.id.add);
        viewAll = (Button) findViewById(R.id.viewAll);
       profile = (ImageView)findViewById(R.id.pro);

        AddData();
        viewAll();
        PhoneNumber();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Glide.with(getApplicationContext())
                .load(user.getPhotoUrl())
               .into(profile);



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(HomeActivity.this,Profile.class);
               startActivity(intent);

            }
        });



        AutoCompleteTextView country =findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,COUNTRIES);
        country.setAdapter(adapter);

        AutoCompleteTextView state =findViewById(R.id.autoCompleteTextView2);
        ArrayAdapter<String> adapter1 =new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,STATES);
        state.setAdapter(adapter1);


    }

    private void PhoneNumber() {
    }

    private static final String[] COUNTRIES = new String[]{
"India","Pakisthan","Australia","United Kingdom","United States","NewYork","Iran","Indonesia","Paris",""
    };
    private static final String[] STATES = new String[]{
      "TamilNadu" ,"AndraPradesh","Karnatake","Kerala" ,"Maharashtra"
            ,"Punjab"
            ,"Gujarat",""
    };
    //tring count = adap
    private void viewAll() {
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
if(res.getCount()== 0){
showMessage("error","no data found");
return;
}
StringBuffer buffer= new StringBuffer();
while(res.moveToNext()) {
    buffer.append("ID:" + res.getString(0) + "\n");
    buffer.append("Roll_number :"+res.getString(1)+"\n");
    buffer.append("Student Name :" + res.getString(2) + "\n");
    buffer.append("date of birth :" + res.getString(3) + "\n");
    buffer.append("address :" + res.getString(4) + "\n");
    buffer.append("standard :" + res.getString(5) + "\n");
    buffer.append("phone number :" + res.getString(6) + "\n");
    buffer.append("attendance  :" + res.getString(7) + "% \n\n");

}
showMessage("Data",buffer.toString());

}


        });

    }

    private void AddData() {
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roll = roll_number.getText().toString();
            String name = student_name.getText().toString();
            String dob = student_dob.getText().toString();
            String addr = address.getText().toString();
            String std = standard.getText().toString();

                boolean isInserted =myDb.insertData(roll_number.getText().toString(),student_name.getText().toString(),student_dob.getText().toString(),
                        address.getText().toString(),standard.getText().toString(),ph_no.getText().toString(),atten.getText().toString());

                if(isInserted == true){
                    Toast.makeText(HomeActivity.this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(HomeActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                }

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
}
