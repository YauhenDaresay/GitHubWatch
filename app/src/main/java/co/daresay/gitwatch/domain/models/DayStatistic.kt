package co.daresay.gitwatch.domain.models

import kotlinx.datetime.LocalDate

data class DayStatistic(
    val weekDay: Int,
    val contributionCount: Int,
    val contributionLevel: String,
    val date: LocalDate,
    val color: String,
)
