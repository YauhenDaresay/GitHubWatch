package co.daresay.gitwatch

import android.app.Application
import co.daresay.gitwatch.di.appModule
import co.daresay.gitwatch.di.networkModule
import co.daresay.gitwatch.di.repositoryModule
import co.daresay.gitwatch.di.viewModelModule
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
