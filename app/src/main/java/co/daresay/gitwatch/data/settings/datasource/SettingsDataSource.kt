package co.daresay.gitwatch.data.settings.datasource

import co.daresay.gitwatch.data.settings.models.AccessTokenModel

interface SettingsDataSource {

    suspend fun getUserName(): String

    suspend fun setUserName(userName: String)

    suspend fun getAccessToken(): AccessTokenModel

    suspend fun setAccessToken(accessToken: AccessTokenModel)
}
