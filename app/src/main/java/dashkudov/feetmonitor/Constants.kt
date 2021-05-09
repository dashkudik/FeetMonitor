package dashkudov.feetmonitor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

object Constants {
    const val DEFAULT_START_VALUE = -1
    const val CHART_ENTRIES_AMOUNT = 12
    const val PREF_NAME = "Name"
    const val KEY_AUTOCONNECTION = "1"
    const val KEY_FULLSCREEN = "2"
    const val KEY_NOTIFICATIONS = "3"
    const val MAC = "20:19:06:04:34:29"
    const val BLUETOOTH_REQUEST_CODE = 666

    fun <T : ViewModel> ViewModelProvider.Factory.create(
        owner: ViewModelStoreOwner,
        modelClass: Class<T>,
    ): T {
        return ViewModelProvider(
            owner,
            this
        ).get(modelClass)
    }

}