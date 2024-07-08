package co.daresay.githubwatch.data.statistics.datasource

import co.daresay.githubwatch.data.statistics.models.ChartDataModel

interface StatisticsDataSource {

    suspend fun getUserStatistics(userName: String): ChartDataModel
}
