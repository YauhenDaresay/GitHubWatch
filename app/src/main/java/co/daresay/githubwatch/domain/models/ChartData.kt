package co.daresay.githubwatch.domain.models

data class ChartData(
    val weeks: List<WeekStatistic> = emptyList(),
)
