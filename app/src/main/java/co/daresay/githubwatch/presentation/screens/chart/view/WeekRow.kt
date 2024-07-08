package co.daresay.githubwatch.presentation.screens.chart.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices.SMALL_ROUND
import co.daresay.githubwatch.domain.models.DayStatistic
import co.daresay.githubwatch.domain.models.MonthStatistic
import co.daresay.githubwatch.domain.models.WeekStatistic
import co.daresay.githubwatch.presentation.theme.GitHubWatchTheme
import co.daresay.githubwatch.presentation.theme.Square
import kotlinx.datetime.LocalDate

@Composable
fun WeekRow(rowItemModel: RowItemModel) {
    BoxWithConstraints(
        propagateMinConstraints = true,
        content = {
            val boxWithConstraintsScope = this
            when (rowItemModel) {
                is RowItemModel.MonthHeaderItemModel -> MonthHeaderComposable(rowItemModel)
                is RowItemModel.WeekItemModel -> WeekComposable(rowItemModel)
            }
        }
    )
}

@Composable
fun WeekComposable(weekItemModelStatistic: RowItemModel.WeekItemModel) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        for (i in 0..6) {
            val day = weekItemModelStatistic.week.contributionDays.find { it.weekDay == i }

            Square(
                modifier = Modifier
                    .weight(1f),
                color = day?.color?.let { Color(android.graphics.Color.parseColor(it)) }
            )
            Box(modifier = Modifier.width(2.dp))
        }
    }
}

@Composable
fun MonthHeaderComposable(monthHeaderItemModel: RowItemModel.MonthHeaderItemModel) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Text(
            textAlign = TextAlign.Start,
            text = "${monthHeaderItemModel.monthStatistic.month}, ${monthHeaderItemModel.monthStatistic.year}: ${monthHeaderItemModel.monthStatistic.contributions}"
        )
    }
}

@Preview(device = SMALL_ROUND, showSystemUi = true)
@Composable
fun WeekRowPreview(@PreviewParameter(WeekStatisticsProvider::class) weekStatistic: RowItemModel) {
    GitHubWatchTheme {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            WeekRow(weekStatistic)
        }
    }
}

class WeekStatisticsProvider : PreviewParameterProvider<RowItemModel> {
    override val values = sequenceOf(
        RowItemModel.MonthHeaderItemModel(
            MonthStatistic(
                month = "January",
                contributions = 1
            )
        ),
        RowItemModel.WeekItemModel(
            WeekStatistic(
                contributionDays = listOf(
                    DayStatistic(
                        weekDay = 0,
                        contributionCount = 1,
                        contributionLevel = "FIRST",
                        date = LocalDate(2003, 2, 2),
                        color = "#40c463"
                    ),
                    DayStatistic(
                        weekDay = 0,
                        contributionCount = 1,
                        contributionLevel = "FIRST",
                        date = LocalDate(2003, 2, 2),
                        color = "#40c463"
                    ),
                    DayStatistic(
                        weekDay = 0,
                        contributionCount = 1,
                        contributionLevel = "FIRST",
                        date = LocalDate(2003, 2, 2),
                        color = "#40c463"
                    ),
                    DayStatistic(
                        weekDay = 0,
                        contributionCount = 1,
                        contributionLevel = "FIRST",
                        date = LocalDate(2003, 2, 2),
                        color = "#40c463"
                    ),
                    DayStatistic(
                        weekDay = 0,
                        contributionCount = 1,
                        contributionLevel = "FIRST",
                        date = LocalDate(2003, 2, 2),
                        color = "#40c463"
                    ), DayStatistic(
                        weekDay = 5,
                        contributionCount = 1,
                        contributionLevel = "FIRST",
                        date = LocalDate(2003, 2, 2),
                        color = "#40c463"
                    ), DayStatistic(
                        weekDay = 6,
                        contributionCount = 1,
                        contributionLevel = "FIRST",
                        date = LocalDate(2003, 2, 2),
                        color = "#ffffff"
                    )
                ), firstDay = LocalDate.parse("2003-02-02")
            )
        )
    )
}
