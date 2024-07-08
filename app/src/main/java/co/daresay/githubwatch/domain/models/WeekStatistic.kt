package co.daresay.githubwatch.domain.models

import kotlinx.datetime.LocalDate

data class WeekStatistic(
    val contributionDays: List<DayStatistic>,
    val firstDay: LocalDate,
)
