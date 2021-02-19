package dashkudov.feetmonitor.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dashkudov.feetmonitor.presentation.fragments.connection.ConnectionViewModel
import dashkudov.feetmonitor.presentation.fragments.home.HomeViewModel
import dashkudov.feetmonitor.presentation.fragments.settings.SettingsViewModel
import dashkudov.feetmonitor.presentation.fragments.stats.StatsViewModel

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(ConnectionViewModel::class)
    abstract fun bindConnectionViewModel(connectionViewModel: ConnectionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StatsViewModel::class)
    abstract fun bindStatsViewModel(statsViewModel: StatsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

}