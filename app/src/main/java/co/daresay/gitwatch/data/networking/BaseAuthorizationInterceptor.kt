package co.daresay.gitwatch.data.networking

import co.daresay.gitwatch.BuildConfig
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.SignatureAlgorithm
import okhttp3.Interceptor
import okhttp3.Response
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.Base64
import java.util.Date

class BaseAuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = getJwt()

        val request = chain.request().newBuilder()
            .addHeader(ACCEPT_HEADER, ACCEPT)
            .addHeader(AUTHORIZATION_HEADER, "$AUTHORIZATION $token")
            .addHeader(API_VERSION_HEADER, API_VERSION)
            .build()
        return chain.proceed(request)
    }

    private fun getJwt(): String {
        val privateKey: PrivateKey = getPrivateKeyFromPemFile()

        val alg: SignatureAlgorithm = Jwts.SIG.RS256 // or PS512, RS256, etc...
        val jwt = Jwts.builder()
            .issuer(GITHUB_CLIENT_ID)
            .expiration(Date(System.currentTimeMillis() + EXPIRATION_JWT_TIME))
            .issuedAt(Date())
            .header()
            .contentType(CONTENT_TYPE).and()
            .signWith(privateKey, alg)
            .compact()
        return jwt
    }

    private fun getPrivateKeyFromPemFile(): PrivateKey {
        var privateKeyPEM: String = BuildConfig.PRIVATE_KEY
        privateKeyPEM = privateKeyPEM.replace(BEGIN_PRIVATE_KEY, "")
            .replace(END_PRIVATE_KEY, "")
            .replace("'''", "")
            .replace("\\s".toRegex(), "")

        // Decode the Base64-encoded string
        val encoded: ByteArray = Base64.getDecoder().decode(privateKeyPEM)

        // Generate the PrivateKey
        val keySpec = PKCS8EncodedKeySpec(encoded)
        val keyFactory = KeyFactory.getInstance(ALGORITHM) // or "EC" if using an EC key
        return keyFactory.generatePrivate(keySpec)
    }

    companion object {
        const val ACCEPT_HEADER = "Accept"
        const val ACCEPT = "application/vnd.github+json"
        const val AUTHORIZATION_HEADER = "Authorization"
        const val AUTHORIZATION = "Bearer"
        const val API_VERSION_HEADER = "X-GitHub-Api-Version"
        const val API_VERSION = "2022-11-28"
        const val ALGORITHM = "RSA"
        const val CONTENT_TYPE = "JWT"
        const val EXPIRATION_JWT_TIME = 600_000
        const val GITHUB_CLIENT_ID = "Iv23liviU15Sm3vwMEnG"
        const val BEGIN_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----"
        const val END_PRIVATE_KEY = "-----END RSA PRIVATE KEY-----"
    }
}
