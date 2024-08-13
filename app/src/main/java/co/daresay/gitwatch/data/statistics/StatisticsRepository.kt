package co.daresay.gitwatch.data.statistics

import co.daresay.gitwatch.domain.models.ChartData

interface StatisticsRepository {

    suspend fun getUserStatistics(userName: String): ChartData
}
