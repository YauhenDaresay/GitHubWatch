package co.daresay.githubwatch.data.settings.datasource

import co.daresay.githubwatch.data.settings.models.AccessTokenModel
import co.daresay.githubwatch.data.settings.models.InstallationModel

interface AuthorisationDataSource {
    suspend fun getAvailableInstallations(): InstallationModel

    suspend fun getAccessToken(installationId: Long): AccessTokenModel
}
