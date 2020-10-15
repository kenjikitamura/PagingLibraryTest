package jp.rainbowdevil.paginglibrarytest

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(module)
        }
    }

    // Koin Module
    private val module : Module = module {
        factory {
            MainViewModel()
        }
    }
}