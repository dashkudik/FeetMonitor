package dashkudov.feetmonitor.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dashkudov.feetmonitor.FeetMonitorApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class, ActivityBuilder::class, AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<FeetMonitorApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}