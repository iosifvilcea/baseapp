package com.blankthings.baseapp.analytics

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