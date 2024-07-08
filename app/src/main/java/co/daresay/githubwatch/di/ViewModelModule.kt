package co.daresay.githubwatch.di

import co.daresay.githubwatch.presentation.screens.chart.ChartViewModel
import co.daresay.githubwatch.presentation.screens.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChartViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
}
