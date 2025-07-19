package com.blankthings.baseapp.analytics

// TODO - Create a wrapper to be used by iOS and Android using expect / actual.
object Analytics {
    private var isTrackingEnabled: Boolean = true

    fun disableTracking() {
        isTrackingEnabled = false
    }

    fun enableTracking() {
        isTrackingEnabled = true
    }

    fun track(event: AnalyticsEvent, properties: Map<String, Any> = emptyMap()) {
        if (isTrackingEnabled) {
            println("Tracking event: $event with properties: $properties")
        }
    }
}