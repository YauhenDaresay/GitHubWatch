package co.daresay.githubwatch.di

import co.daresay.githubwatch.data.settings.AuthorisationRepository
import co.daresay.githubwatch.data.settings.AuthorisationRepositoryImpl
import co.daresay.githubwatch.data.settings.SettingsRepository
import co.daresay.githubwatch.data.settings.SettingsRepositoryImpl
import co.daresay.githubwatch.data.settings.datasource.AuthorisationDataSource
import co.daresay.githubwatch.data.settings.datasource.AuthorisationDataSourceImpl
import co.daresay.githubwatch.data.settings.datasource.LocalSettingsDataSource
import co.daresay.githubwatch.data.settings.datasource.SettingsDataSource
import co.daresay.githubwatch.data.statistics.StatisticsRepository
import co.daresay.githubwatch.data.statistics.StatisticsRepositoryImpl
import co.daresay.githubwatch.data.statistics.datasource.RemoteStatisticsDataSource
import co.daresay.githubwatch.data.statistics.datasource.StatisticsDataSource
import org.koin.dsl.module

val repositoryModule = module {

    single<StatisticsRepository> { StatisticsRepositoryImpl(get()) }
    single<StatisticsDataSource> { RemoteStatisticsDataSource(get()) }

    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<SettingsDataSource> { LocalSettingsDataSource(get()) }
    single<AuthorisationDataSource> { AuthorisationDataSourceImpl(get()) }
    single<AuthorisationRepository> { AuthorisationRepositoryImpl(get(), get()) }
}
