package co.daresay.gitwatch.domain.models

import kotlinx.datetime.LocalDate

data class WeekStatistic(
    val contributionDays: List<DayStatistic>,
    val firstDay: LocalDate,
)
