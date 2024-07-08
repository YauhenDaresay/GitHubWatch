package co.daresay.githubwatch.data.settings

interface AuthorisationRepository {

    suspend fun getAccessToken(): String
}
