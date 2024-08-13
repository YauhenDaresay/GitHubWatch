package co.daresay.gitwatch.data.settings.datasource

import co.daresay.gitwatch.data.settings.models.AccessTokenModel
import co.daresay.gitwatch.data.settings.models.InstallationModel

interface AuthorisationDataSource {
    suspend fun getAvailableInstallations(): InstallationModel

    suspend fun getAccessToken(installationId: Long): AccessTokenModel
}
