package co.daresay.gitwatch.di

import co.daresay.gitwatch.presentation.screens.chart.ChartViewModel
import co.daresay.gitwatch.presentation.screens.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChartViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
}
