package dashkudov.feetmonitor.di

import dagger.Binds
import dagger.Module
import dashkudov.feetmonitor.domain.repository.AppRepository
import dashkudov.feetmonitor.gateway.AppRepositoryImpl

@Module
abstract class RepositoryBuilder {
    @Binds
    abstract fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository
}