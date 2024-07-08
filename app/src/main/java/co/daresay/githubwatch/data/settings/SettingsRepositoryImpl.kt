package co.daresay.githubwatch.data.settings

import co.daresay.githubwatch.data.settings.datasource.SettingsDataSource

class SettingsRepositoryImpl(
    private val settingsDataSource: SettingsDataSource,
) :
    SettingsRepository {

    override suspend fun getUserName(): String {
        return settingsDataSource.getUserName()
    }

    override suspend fun setUserName(userName: String) {
        settingsDataSource.setUserName(userName)
    }
}
