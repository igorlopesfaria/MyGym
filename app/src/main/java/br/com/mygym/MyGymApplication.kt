package br.com.mygym

import android.app.Application
import br.com.mygym.sdk.database.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyGymApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyGymApplication)
            koin.loadModules(
                listOf(
                    databaseModule
                )
            )
            koin.createRootScope()
        }
    }
}