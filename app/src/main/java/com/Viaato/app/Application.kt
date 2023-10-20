package com.Viaato.app

import android.app.Application
import ru.dgis.sdk.*
import ru.dgis.sdk.positioning.DefaultLocationSource
import ru.dgis.sdk.positioning.registerPlatformLocationSource

class MyApplication : Application() {
    lateinit var sdkContext: Context
    override fun onCreate() {
        super.onCreate()
        sdkContext = initializeDGis(
            appContext = this
        )
    }
    fun registerServices() {
        val locationSource = DefaultLocationSource(applicationContext)
        registerPlatformLocationSource(sdkContext, locationSource)

    }
}
val Application.sdkContext: Context
    get() = (this as com.Viaato.app.MyApplication).sdkContext