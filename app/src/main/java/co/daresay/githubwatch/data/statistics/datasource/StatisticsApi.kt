package co.daresay.githubwatch.data.statistics.datasource

import retrofit2.http.GET

interface StatisticsApi {

    @GET("users/kiolk/repos")
    suspend fun getUserStatistics(): List<Any>
}
