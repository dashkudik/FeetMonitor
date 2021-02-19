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
}