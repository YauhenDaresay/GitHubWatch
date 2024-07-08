package co.daresay.githubwatch.domain.models

import kotlinx.datetime.LocalDate

data class DayStatistic(
    val weekDay: Int,
    val contributionCount: Int,
    val contributionLevel: String,
    val date: LocalDate,
    val color: String,
)
