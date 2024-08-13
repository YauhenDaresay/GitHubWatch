package co.daresay.gitwatch.data.settings

interface AuthorisationRepository {

    suspend fun getAccessToken(): String
}
