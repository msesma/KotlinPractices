package sesma.eu.kotlinweather.ui

import android.app.Application
import sesma.eu.kotlinweather.extensions.DelegatesExt

class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
