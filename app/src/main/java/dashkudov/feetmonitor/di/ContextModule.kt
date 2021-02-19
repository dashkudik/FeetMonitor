package dashkudov.feetmonitor.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dashkudov.feetmonitor.FeetMonitorApp
import javax.inject.Singleton

@Module(includes = [ViewModelBuilder::class])
class ContextModule {
    @Provides
    @Singleton
    fun provideContext(application: FeetMonitorApp): Context {
        return application
    }
}