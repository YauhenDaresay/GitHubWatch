package co.daresay.githubwatch.presentation.screens.chart.view

import co.daresay.githubwatch.domain.models.MonthStatistic
import co.daresay.githubwatch.domain.models.WeekStatistic

sealed class RowItemModel {
    data class WeekItemModel(val week: WeekStatistic) : RowItemModel()
    data class MonthHeaderItemModel(val monthStatistic: MonthStatistic) : RowItemModel()
}
