package co.daresay.gitwatch.presentation.screens.chart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.daresay.gitwatch.data.settings.SettingsRepository
import co.daresay.gitwatch.data.statistics.StatisticsRepository
import co.daresay.gitwatch.domain.models.ChartData
import co.daresay.gitwatch.domain.models.DayStatistic
import co.daresay.gitwatch.domain.models.MonthStatistic
import co.daresay.gitwatch.presentation.screens.chart.view.ChartModel
import co.daresay.gitwatch.presentation.screens.chart.view.RowItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import java.util.Locale

class ChartViewModel(
    private val settingsRepository: SettingsRepository,
    private val statisticsRepository: StatisticsRepository
) : ViewModel() {

    private val _chartData = MutableStateFlow(ChartModel(weeks = emptyList()))
    val chartData = _chartData.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    var uiState by mutableStateOf(UiState())
        private set

    init {
        fetchChartData()
    }

    private fun fetchChartData() {
        viewModelScope.launch {
            val userName = settingsRepository.getUserName()

            if (userName.isEmpty()) {
                uiState = uiState.copy(openSettings = true)
            } else {
                val chartData = statisticsRepository.getUserStatistics(userName)
                _chartData.emit(chartData.toChartModel())
                _isLoading.value = false
            }
        }
    }

    fun onRefreshPress() {
        _isLoading.value = true
        fetchChartData()
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val chartData: ChartData = ChartData(),
    val openSettings: Boolean = false
)

private fun ChartData.toChartModel(): ChartModel {
    val list: List<DayStatistic> = this.weeks.flatMap { it.contributionDays }
    val groupByMonth = list.groupBy { "${it.date.year}-${it.date.monthNumber}" }
    val rowItemModels = mutableListOf<RowItemModel>()

    this.weeks.forEachIndexed { index, it ->
        val firstDay = it.firstDay
        if (index == this.weeks.size - 1) {
            rowItemModels.add(RowItemModel.WeekItemModel(it))
            rowItemModels.add(getMonthStatistics(firstDay, groupByMonth, isCurrentMonth = true))
        } else {
            if (firstDay.dayOfMonth == 1) {
                rowItemModels.add(getMonthStatistics(firstDay, groupByMonth))
                rowItemModels.add(RowItemModel.WeekItemModel(it))
            } else {
                rowItemModels.add(RowItemModel.WeekItemModel(it))
            }
        }
    }

    return ChartModel(weeks = rowItemModels)
}

fun getMonthStatistics(
    firstDay: LocalDate,
    monthStats: Map<String, List<DayStatistic>>,
    isCurrentMonth: Boolean = false
): RowItemModel.MonthHeaderItemModel {
    var month = firstDay.monthNumber
    var year = firstDay.year

    if (!isCurrentMonth) {
        month = if (firstDay.monthNumber > 1) firstDay.monthNumber - 1 else 12
        year = if (firstDay.monthNumber > 1) firstDay.year else firstDay.year - 1
    }

    return RowItemModel.MonthHeaderItemModel(
        MonthStatistic(
            month = LocalDate(year, month, 1).month.name.substring(0, 1)
                .uppercase(Locale.getDefault()) + LocalDate(
                year,
                month,
                1
            ).month.name.substring(1).lowercase(Locale.getDefault()),
            year = year,
            contributions = monthStats["${year}-${month}"]?.sumOf { it.contributionCount }
                ?: 0
        )
    )
}
