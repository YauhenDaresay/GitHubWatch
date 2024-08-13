package co.daresay.gitwatch.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.dsl.module

private const val APP_PREFERENCES = "app_prefs"

val appModule = module {
    single<SharedPreferences> {
        get<Context>().getSharedPreferences(
            APP_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }
}
