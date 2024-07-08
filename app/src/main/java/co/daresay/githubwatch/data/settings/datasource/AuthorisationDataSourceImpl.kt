package co.daresay.githubwatch.data.settings.datasource

import co.daresay.githubwatch.data.settings.models.AccessTokenModel
import co.daresay.githubwatch.data.settings.models.InstallationModel

class AuthorisationDataSourceImpl(private val authorisationApi: AuthorisationApi) :
    AuthorisationDataSource {
    override suspend fun getAvailableInstallations(): InstallationModel {
        return authorisationApi.getAvailableInstallations()
    }

    override suspend fun getAccessToken(installationId: Long): AccessTokenModel {
        return authorisationApi.getAccessToken(installationId)
    }
}
