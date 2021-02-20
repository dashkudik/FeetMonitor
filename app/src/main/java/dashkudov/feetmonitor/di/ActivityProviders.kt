package dashkudov.feetmonitor.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dashkudov.feetmonitor.presentation.fragments.home.HomeFragment
import dashkudov.feetmonitor.presentation.fragments.settings.SettingsFragment
import dashkudov.feetmonitor.presentation.fragments.stats.StatisticsFragment

@Module
abstract class ActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideStatisticsFragment(): StatisticsFragment

    @ContributesAndroidInjector
    abstract fun provideSettingsFragment(): SettingsFragment
}