package com.example.weatherapplication.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.example.weatherapplication.Util

class RestartBackgroundService : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(Util.getShowNotification(context!!)) {
            Log.i("Broadcast Listened", "Service tried to stop")
            Toast.makeText(context, "Service restarted", Toast.LENGTH_SHORT).show()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context!!.startForegroundService(Intent(context, LocationService::class.java))
            } else {
                context!!.startService(Intent(context, LocationService::class.java))
            }
        }
    }
}