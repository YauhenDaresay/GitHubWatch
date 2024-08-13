package co.daresay.gitwatch.data.statistics.datasource

import co.daresay.gitwatch.UserQuery
import co.daresay.gitwatch.data.statistics.models.ChartDataModel
import com.apollographql.apollo3.ApolloClient

class RemoteStatisticsDataSource(
    private val graphqlClient: ApolloClient
) : StatisticsDataSource {
    override suspend fun getUserStatistics(userName: String): ChartDataModel {
        val userResponse = graphqlClient.query(UserQuery(userName)).execute()

        return ChartDataModel(userResponse.data?.user)
    }
}
