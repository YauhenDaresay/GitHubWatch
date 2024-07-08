package co.daresay.githubwatch.data.statistics

import co.daresay.githubwatch.domain.models.ChartData

interface StatisticsRepository {

    suspend fun getUserStatistics(userName: String): ChartData
}
