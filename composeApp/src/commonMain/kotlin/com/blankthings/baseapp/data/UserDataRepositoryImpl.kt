package com.blankthings.baseapp.data

import com.blankthings.baseapp.model.DarkThemeConfig
import com.blankthings.baseapp.model.UserData
import kotlinx.coroutines.flow.Flow

internal class UserDataRepositoryImpl: UserDataRepository {
    override val userData: Flow<UserData>
        get() = TODO("Get from DataStore")

    override suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        TODO("Set to DataStore")
    }
}