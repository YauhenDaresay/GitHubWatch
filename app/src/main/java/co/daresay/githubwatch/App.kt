package co.daresay.githubwatch

import android.app.Application
import co.daresay.githubwatch.di.appModule
import co.daresay.githubwatch.di.networkModule
import co.daresay.githubwatch.di.repositoryModule
import co.daresay.githubwatch.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(viewModelModule, networkModule, repositoryModule, appModule)
        }
    }
}
