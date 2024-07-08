package co.daresay.githubwatch.data.networking

import co.daresay.githubwatch.data.settings.AuthorisationRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val authorizationRepository: AuthorisationRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = runBlocking {
            authorizationRepository.getAccessToken()
        }
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}
