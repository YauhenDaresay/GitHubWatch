package co.daresay.gitwatch.presentation.screens.chart.view

import co.daresay.gitwatch.domain.models.MonthStatistic
import co.daresay.gitwatch.domain.models.WeekStatistic

sealed class RowItemModel {
    data class WeekItemModel(val week: WeekStatistic) : RowItemModel()
    data class MonthHeaderItemModel(val monthStatistic: MonthStatistic) : RowItemModel()
}
