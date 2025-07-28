package com.blankthings.baseapp.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class Note(
    val id: Int,
    val title: String,
    val content: String,
    val category: Category,
    val date: Instant
)