package dashkudov.feetmonitor

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dashkudov.feetmonitor.di.DaggerAppComponent

class FeetMonitorApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }

    companion object {
        const val UUID_STRING = "0000111e-0000-1000-8000-00805f9b34fb"
        const val NAME = "Dima"
    }
}