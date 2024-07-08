package co.daresay.githubwatch.data.settings.datasource

import co.daresay.githubwatch.data.settings.models.AccessTokenModel

interface SettingsDataSource {

    suspend fun getUserName(): String

    suspend fun setUserName(userName: String)

    suspend fun getAccessToken(): AccessTokenModel

    suspend fun setAccessToken(accessToken: AccessTokenModel)
}
