package dashkudov.feetmonitor.presentation

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MonitorService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CoroutineScope(Default).launch {
            while (true) {
                delay(2000)
                Log.i("TEST", "I am working holy shit's")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    /* private fun startForegroundService() {

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, "1")
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.icon_stats)
            .setContentTitle("Running app")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingActivity())

        startForeground(1, notificationBuilder.build())

    }

    private fun getMainActivityPendingActivity(): PendingIntent {
        return PendingIntent.getActivity(
            this,
            1,
            Intent(this, MainActivity::class.java),
            FLAG_UPDATE_CURRENT
        )
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel("1", "Monitor", NotificationManager.IMPORTANCE_LOW)
        notificationManager.createNotificationChannel(channel)
    } */
}