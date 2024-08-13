package co.daresay.gitwatch.domain.models

data class ChartData(
    val weeks: List<WeekStatistic> = emptyList(),
)
