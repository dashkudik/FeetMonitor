package dashkudov.feetmonitor.domain.repository

interface AppRepository {
    fun setFullscreenMode(isFullscreen: Boolean)
    fun setNotifications(isNotifications: Boolean)
    fun setAutoconnection(isAutoconnection: Boolean)
    fun getFullscreenMode(): Boolean
    fun getNotifications(): Boolean
    fun getAutoconnection(): Boolean
}