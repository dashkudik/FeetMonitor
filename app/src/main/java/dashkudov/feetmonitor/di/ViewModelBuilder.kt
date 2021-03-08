package dashkudov.feetmonitor.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        (RepositoryBuilder::class),
        (AppViewModelBuilder::class)
    ]
)
abstract class ViewModelBuilder {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}