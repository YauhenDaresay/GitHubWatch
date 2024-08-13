package co.daresay.gitwatch.data.settings.datasource

import android.content.SharedPreferences
import co.daresay.gitwatch.data.settings.models.AccessTokenModel

// TODO added logic for store settings in encrypted variant
class LocalSettingsDataSource(private val sharedPreferences: SharedPreferences) :
    SettingsDataSource {

    override suspend fun getUserName(): String {
        return sharedPreferences.getString(USER_NAME, "").orEmpty()
    }

    override suspend fun setUserName(userName: String) {
        sharedPreferences.edit().putString(USER_NAME, userName).apply()
    }

    override suspend fun getAccessToken(): AccessTokenModel {
        val token = sharedPreferences.getString(ACCESS_TOKEN, "").orEmpty()
        val expiresAt = sharedPreferences.getString(EXPIRES_AT, "").orEmpty()

        return AccessTokenModel(token, expiresAt)
    }

    override suspend fun setAccessToken(accessToken: AccessTokenModel) {
        sharedPreferences.edit()
            .putString(EXPIRES_AT, accessToken.expiresAt)
            .putString(ACCESS_TOKEN, accessToken.token)
            .apply()
    }

    private companion object {
        private const val USER_NAME = "user_name"
        private const val ACCESS_TOKEN = "access_token"
        private const val EXPIRES_AT = "expires_at"
    }
}
