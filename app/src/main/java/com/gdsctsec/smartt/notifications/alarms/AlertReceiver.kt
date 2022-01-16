package com.gdsctsec.smartt.ui.notifications.alarms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.gdsctsec.smartt.ui.notifications.NotificationHelper
import com.gdsctsec.smartt.ui.notifications.getLecture
import com.gdsctsec.smartt.viewmodel.EditScreenViewModel



class AlertReceiver() : BroadcastReceiver() {

    private val id = 1

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationHelper = NotificationHelper(context)
        val key = intent?.getStringExtra("requestCode")?.toInt()
        val nb = notificationHelper.getNotifications(
            "lecture alert", "Time for math lecture"
        )
        notificationHelper.notificationManager?.notify(id, nb.build())
    }
}