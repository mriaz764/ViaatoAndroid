package com.Viaato.app

import ru.dgis.sdk.*

fun initializeDGis(appContext: android.content.Context): Context {
    // Logging settings
    val logOptions = LogOptions(
        LogLevel.VERBOSE
    )
// HTTP client settings
    val httpOptions = HttpOptions(
        useCache = false
    )
// Consent to personal data processing
    val dataCollectConsent = PersonalDataCollectionConsent.GRANTED

    return DGis.initialize(
        appContext,
        dataCollectConsent = dataCollectConsent,
        logOptions = logOptions,
        httpOptions = httpOptions,
        keySource = KeySource(KeyFromAsset("dgissdk-3.key")),
    )
}
