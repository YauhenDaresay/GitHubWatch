package co.daresay.gitwatch.data.statistics.datasource

import co.daresay.gitwatch.data.statistics.models.ChartDataModel

interface StatisticsDataSource {

    suspend fun getUserStatistics(userName: String): ChartDataModel
}
