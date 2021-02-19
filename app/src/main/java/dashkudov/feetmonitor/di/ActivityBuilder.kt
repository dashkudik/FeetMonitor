package dashkudov.feetmonitor.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dashkudov.feetmonitor.presentation.MainActivity

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [ActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity
}