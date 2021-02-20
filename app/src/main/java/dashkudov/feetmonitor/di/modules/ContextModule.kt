package dashkudov.feetmonitor.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dashkudov.feetmonitor.FeetMonitorApp
import dashkudov.feetmonitor.di.ViewModelBuilder
import javax.inject.Singleton

@Module(includes = [ViewModelBuilder::class])
class ContextModule {
    @Provides
    @Singleton
    fun provideContext(application: FeetMonitorApp): Context {
        return application
    }
}