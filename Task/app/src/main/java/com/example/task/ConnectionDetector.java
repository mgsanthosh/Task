package com.example.task;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class ConnectionDetector {
    Context context;
    public ConnectionDetector(Context context) {
        this.context = context;

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean isConnected(){
        ConnectivityManager connectivity  = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if(connectivity != null){
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if(info != null){
                if(((NetworkInfo) info).getState()  == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
        return false;
    }
}
