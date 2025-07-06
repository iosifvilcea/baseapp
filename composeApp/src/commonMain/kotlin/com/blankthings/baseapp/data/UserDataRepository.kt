package com.blankthings.baseapp.data

import com.blankthings.baseapp.model.DarkThemeConfig
import com.blankthings.baseapp.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig)
}