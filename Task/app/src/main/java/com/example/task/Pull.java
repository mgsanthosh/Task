package com.example.task;

import android.app.DirectAction;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.CancellationSignal;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.function.Consumer;

public class Pull extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarConfiguration mAppBarConfiguration;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    ImageView my_img;
    public GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.open_nav_drawer, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        updateHeader();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pull, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(Pull.this,Profile.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetDirectActions(@NonNull CancellationSignal cancellationSignal, @NonNull Consumer<List<DirectAction>> callback) {
        super.onGetDirectActions(cancellationSignal, callback);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(Pull.this,HomeActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(Pull.this,EnterAttendance.class);
            startActivity(i);
            //Intent intent = new Intent(Home.this,Medicine.class);
            //startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

            Intent i = new Intent(Pull.this, AllStudents.class);
            startActivity(i);

        } else if (id == R.id.nav_tools) {
            Intent i = new Intent(Pull.this,ContactParents.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {
           Intent i = new Intent(Pull.this, CurrentLocation.class);
            startActivity(i);

        } else if (id == R.id.nav_send) {

            Intent i = new Intent(Pull.this, Sms.class);
            startActivity(i);



        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void updateHeader() {
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView user_name = headerView.findViewById(R.id.user_name);
        TextView user_email = headerView.findViewById(R.id.user_email);
        my_img = headerView.findViewById(R.id.my_img);


        user_email.setText(currentUser.getEmail());
        user_name.setText(currentUser.getDisplayName());

        Glide.with(getApplicationContext())
                .load(currentUser.getPhotoUrl())
                .into(my_img);

    }


    }
