package com.blankthings.baseapp.analytics

class Analytics(
    val isTrackingEnabled: () -> Boolean,
) {
    fun track(event: AnalyticsEvent, properties: Map<String, Any> = emptyMap()) {
        if (isTrackingEnabled()) {
            println("Tracking event: $event with properties: $properties")
        }
    }
}