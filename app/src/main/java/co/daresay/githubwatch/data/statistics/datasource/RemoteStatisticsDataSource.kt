package co.daresay.githubwatch.data.statistics.datasource

import co.daresay.githubwatch.UserQuery
import co.daresay.githubwatch.data.statistics.models.ChartDataModel
import com.apollographql.apollo3.ApolloClient

class RemoteStatisticsDataSource(
    private val graphqlClient: ApolloClient
) : StatisticsDataSource {
    override suspend fun getUserStatistics(userName: String): ChartDataModel {
        val userResponse = graphqlClient.query(UserQuery(userName)).execute()

        return ChartDataModel(userResponse.data?.user)
    }
}
