package com.example.task;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class Login extends AppCompatActivity {
    private EditText user_name;
    private EditText user_password;
    private Button login_button;
    FirebaseAuth mAuth;
    String uname , upass;
    private TextView txt;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION   |
                SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();




        user_name = (EditText) findViewById(R.id.user_name);
        user_password =(EditText) findViewById(R.id.user_password);
        login_button = (Button) findViewById(R.id.login_btn);
        txt = (TextView) findViewById(R.id.txt);

        txt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                connection();



                    Intent intent = new Intent(Login.this, Glogin.class);
                    startActivity(intent);



            }
        });
        //signin = (Button) findViewById(R.id.sign_in_button);

//        signin.setOnClickListener(new View.OnClickListener() {
  //          @Override
    //        public void onClick(View view) {
      //          Intent intent  = new Intent(Login.this,google.class);
        //        startActivity(intent);
          //  }
        //});





        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(Login.this,HomeActivity.class);
                //startActivity(i);
                connection();
                uname = user_name.getText().toString();
                upass = user_password.getText().toString();

                //Intent intent = new Intent(Login.this,HomeActivity.class);
                //startActivity(intent);
                if(uname.isEmpty() || upass.isEmpty()){
                    Toast.makeText(Login.this,"USERNAME OR PASSSWORD IS INCORRECT",Toast.LENGTH_LONG).show();

                }
                else{
                    email(uname,upass);
                }
            }
        });



    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void connection() {
        if(isNetworkAvailable(this)){

        }
        else{
            Toast.makeText(Login.this,"Not Connected",Toast.LENGTH_LONG).show();
        }
    }
    //GOOGLE SIGN IN CODE




    public void email(String uname ,String upass){
        mAuth.signInWithEmailAndPassword(uname,upass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                                updateUI();
                        }
                            else{
                                Toast.makeText(Login.this,"AUNTHENTICATION FAILED",Toast.LENGTH_LONG).show();
                                }
            }
        });

    }
private void updateUI(){
    Intent intent = new Intent(Login.this,Pull.class);
    startActivity(intent);
    finish();
}


}
