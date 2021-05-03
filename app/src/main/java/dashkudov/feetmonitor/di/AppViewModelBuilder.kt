package dashkudov.feetmonitor.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dashkudov.feetmonitor.presentation.FullChartViewModel
import dashkudov.feetmonitor.presentation.MainViewModel
import dashkudov.feetmonitor.presentation.fragments.home.HomeViewModel
import dashkudov.feetmonitor.presentation.fragments.settings.SettingsViewModel
import dashkudov.feetmonitor.presentation.fragments.stats.StatisticsViewModel

@Module
abstract class AppViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StatisticsViewModel::class)
    abstract fun bindStatisticsViewModel(statisticsViewModel: StatisticsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FullChartViewModel::class)
    abstract fun bindFullChartViewModel(fullChartViewModel: FullChartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}