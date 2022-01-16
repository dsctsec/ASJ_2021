package com.gdsctsec.smartt.ui.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.gdsctsec.smartt.R

class NotificationHelper(base: Context?) : ContextWrapper(base) {
     var notificationManager:NotificationManager?=null
    companion object {
        const val channelId="channelId"
        const val channelName="Lecture alert"
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannels() {
        val notificationChannel=NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_DEFAULT)
        notificationChannel.enableLights(true)
        notificationChannel.enableVibration(true)
        notificationChannel.lightColor= R.color.design_default_color_primary
        notificationChannel.lockscreenVisibility=Notification.VISIBILITY_PRIVATE

        getManager()?.createNotificationChannel(notificationChannel)
    }


    private fun getManager():NotificationManager?{
        if (notificationManager==null){
            notificationManager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        return notificationManager
    }

    fun getNotifications(title:String,message:String):NotificationCompat.Builder{
            return NotificationCompat.Builder(applicationContext, channelId)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.app_logo)
    }
}