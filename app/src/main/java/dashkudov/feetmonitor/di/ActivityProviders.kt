package dashkudov.feetmonitor.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dashkudov.feetmonitor.presentation.fragments.connection.ConnectionFragment
import dashkudov.feetmonitor.presentation.fragments.home.HomeFragment
import dashkudov.feetmonitor.presentation.fragments.settings.SettingsFragment
import dashkudov.feetmonitor.presentation.fragments.stats.StatsFragment

@Module
abstract class ActivityProviders {
    @ContributesAndroidInjector
    abstract fun provideConnectionFragment(): ConnectionFragment

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun provideStatsFragment(): StatsFragment

    @ContributesAndroidInjector
    abstract fun provideSettingsFragment(): SettingsFragment
}