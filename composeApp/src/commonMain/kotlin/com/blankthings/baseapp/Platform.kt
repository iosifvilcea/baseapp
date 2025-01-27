package com.blankthings.baseapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform