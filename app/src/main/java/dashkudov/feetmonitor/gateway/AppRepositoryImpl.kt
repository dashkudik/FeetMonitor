package dashkudov.feetmonitor.gateway

import android.content.Context
import android.content.SharedPreferences
import dashkudov.feetmonitor.Constants.KEY_AUTOCONNECTION
import dashkudov.feetmonitor.Constants.KEY_FULLSCREEN
import dashkudov.feetmonitor.Constants.KEY_NOTIFICATIONS
import dashkudov.feetmonitor.Constants.PREF_NAME
import dashkudov.feetmonitor.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
) : AppRepository {
    var context: Context? = null
    val pref: SharedPreferences
        get() {
            return context?.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)!!
        }

    override fun setAutoconnection(isAutoconnection: Boolean) {
        pref.edit()?.putBoolean(KEY_AUTOCONNECTION, isAutoconnection)?.apply()
    }

    override fun setNotifications(isNotifications: Boolean) {
        pref.edit()?.putBoolean(KEY_NOTIFICATIONS, isNotifications)?.apply()
    }

    override fun setFullscreenMode(isFullscreen: Boolean) {
        pref.edit()?.putBoolean(KEY_FULLSCREEN, isFullscreen)?.apply()
    }

    override fun getAutoconnection(): Boolean {
        return pref.getBoolean(KEY_AUTOCONNECTION, false)
    }

    override fun getFullscreenMode(): Boolean {
        return pref.getBoolean(KEY_FULLSCREEN, false)
    }

    override fun getNotifications(): Boolean {
        return pref.getBoolean(KEY_NOTIFICATIONS, false)
    }
}