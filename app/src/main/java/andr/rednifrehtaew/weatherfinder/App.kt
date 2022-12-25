package andr.rednifrehtaew.weatherfinder

import android.app.Application
import andr.rednifrehtaew.weatherfinder.di.appModule
import andr.rednifrehtaew.weatherfinder.di.dataModule
import andr.rednifrehtaew.weatherfinder.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }

    }
}