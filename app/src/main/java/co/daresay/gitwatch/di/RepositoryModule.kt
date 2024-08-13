package co.daresay.gitwatch.di

import co.daresay.gitwatch.data.settings.AuthorisationRepository
import co.daresay.gitwatch.data.settings.AuthorisationRepositoryImpl
import co.daresay.gitwatch.data.settings.SettingsRepository
import co.daresay.gitwatch.data.settings.SettingsRepositoryImpl
import co.daresay.gitwatch.data.settings.datasource.AuthorisationDataSource
import co.daresay.gitwatch.data.settings.datasource.AuthorisationDataSourceImpl
import co.daresay.gitwatch.data.settings.datasource.LocalSettingsDataSource
import co.daresay.gitwatch.data.settings.datasource.SettingsDataSource
import co.daresay.gitwatch.data.statistics.StatisticsRepository
import co.daresay.gitwatch.data.statistics.StatisticsRepositoryImpl
import co.daresay.gitwatch.data.statistics.datasource.RemoteStatisticsDataSource
import co.daresay.gitwatch.data.statistics.datasource.StatisticsDataSource
import org.koin.dsl.module

val repositoryModule = module {

    single<StatisticsRepository> { StatisticsRepositoryImpl(get()) }
    single<StatisticsDataSource> { RemoteStatisticsDataSource(get()) }

    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<SettingsDataSource> { LocalSettingsDataSource(get()) }
    single<AuthorisationDataSource> { AuthorisationDataSourceImpl(get()) }
    single<AuthorisationRepository> { AuthorisationRepositoryImpl(get(), get()) }
}
