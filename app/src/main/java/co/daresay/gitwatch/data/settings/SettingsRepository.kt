package co.daresay.gitwatch.data.settings

interface SettingsRepository {

    suspend fun getUserName(): String

    suspend fun setUserName(userName: String)
}
