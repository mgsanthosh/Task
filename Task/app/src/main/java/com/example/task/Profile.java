package com.example.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.printservice.PrintService;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

public class Profile extends Glogin {
    public FirebaseAuth mAuth;
    public TextView mStatusTextView;
    public TextView mDetailTextView;
    ImageView img;
    Button logout;
    TextView numD;


    @Override
    public void updateUI(FirebaseUser user) {
        super.updateUI(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION   |
                SYSTEM_UI_FLAG_LAYOUT_STABLE | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        mStatusTextView = findViewById(R.id.mStatusTextView);
        mDetailTextView = findViewById(R.id.mDetailTextView);
        numD = findViewById(R.id.numD);




        img = (ImageView) findViewById(R.id.img);
        logout = (Button) findViewById(R.id.logout);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String myUrl;
        mStatusTextView.setText(user.getEmail());
        mDetailTextView.setText( user.getDisplayName());

        Glide.with(getApplicationContext())
                .load(user.getPhotoUrl())
                .into(img);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               revokeAccess1();
                

            }
            
        });

    }

    public void revokeAccess1() {
        mAuth.signOut();
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        numD.setVisibility(View.VISIBLE);
                        Intent i = new Intent(Profile.this,Login.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();

                    }

                });

    }


}
