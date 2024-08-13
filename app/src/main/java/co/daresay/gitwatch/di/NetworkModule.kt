package co.daresay.gitwatch.di

import co.daresay.gitwatch.data.networking.AuthorizationInterceptor
import co.daresay.gitwatch.data.networking.BaseAuthorizationInterceptor
import co.daresay.gitwatch.data.settings.datasource.AuthorisationApi
import co.daresay.gitwatch.data.statistics.datasource.StatisticsApi
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

private const val LOGGING_INTERCEPTOR = "logging"
private const val AUTHORIZATION_INTERCEPTOR = "authorization_interceptor"
private const val BASE_AUTHORIZATION_INTERCEPTOR = "base_authorization_interceptor"
private const val BASE_AUTHORIZATION_OK_HTTP = "base_authorization_ok_http"
private const val BASE_AUTHORIZATION_RETROFIT = "base_authorization_retrofit"
private const val AUTHORIZATION_OK_HTTP = "authorization_ok_http"
private const val AUTHORIZATION_RETROFIT = "authorization_retrofit"
private const val BASE_URL = "https://api.github.com/"
private const val GRAPHQL_BASE_URL = "https://api.github.com/graphql"

val networkModule = module {

    single<Interceptor>(named(LOGGING_INTERCEPTOR)) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<Interceptor>(named(AUTHORIZATION_INTERCEPTOR)) {
        AuthorizationInterceptor(get())
    }

    single<Interceptor>(named(BASE_AUTHORIZATION_INTERCEPTOR)) {
        BaseAuthorizationInterceptor()
    }

    single<Json> { Json { ignoreUnknownKeys = true } }

    single<OkHttpClient>(named(AUTHORIZATION_OK_HTTP)) {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named(LOGGING_INTERCEPTOR)))
            .addInterceptor(get<Interceptor>(named(AUTHORIZATION_INTERCEPTOR)))
            .build()
    }

    single<OkHttpClient>(named(BASE_AUTHORIZATION_OK_HTTP)) {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>(named(LOGGING_INTERCEPTOR)))
            .addInterceptor(get<Interceptor>(named(BASE_AUTHORIZATION_INTERCEPTOR)))
            .build()
    }

    single<ApolloClient> {
        ApolloClient.Builder()
            .serverUrl(GRAPHQL_BASE_URL)
            .okHttpClient(get<OkHttpClient>(named(AUTHORIZATION_OK_HTTP)))
            .build()
    }

    single<Retrofit>(named(AUTHORIZATION_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>(named(AUTHORIZATION_OK_HTTP)))
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single<Retrofit>(named(BASE_AUTHORIZATION_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>(named(BASE_AUTHORIZATION_OK_HTTP)))
            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single<StatisticsApi> { get<Retrofit>(named(AUTHORIZATION_RETROFIT)).create(StatisticsApi::class.java) }
    single<AuthorisationApi> {
        get<Retrofit>(named(BASE_AUTHORIZATION_RETROFIT)).create(
            AuthorisationApi::class.java
        )
    }
}
