package com.gdsctsec.smartt.ui.notifications.alarms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsctsec.smartt.ui.edit.EditScreenFragment
import com.gdsctsec.smartt.ui.main.HomeScreenFragment
import com.gdsctsec.smartt.ui.main.lectureNew
import com.gdsctsec.smartt.ui.notifications.NotificationHelper
import com.gdsctsec.smartt.ui.notifications.getLecture
import com.gdsctsec.smartt.viewmodel.EditScreenViewModel
import com.gdsctsec.smartt.viewmodel.EditscreenViewmodelfactory


class AlertReceiver() : BroadcastReceiver() {

    private val id=1

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationHelper=NotificationHelper(context)
        val key=intent?.getStringExtra("requestCode")?.toInt()
        Log.e("key",key.toString())
        val nb=notificationHelper.getNotifications("lecture alert","Time for ${lectureNew.get(key)} lecture")
        notificationHelper.notificationManager?.notify(id,nb.build())
    }
}