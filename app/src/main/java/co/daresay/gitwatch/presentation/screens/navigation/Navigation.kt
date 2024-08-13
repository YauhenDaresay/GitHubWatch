package co.daresay.gitwatch.presentation.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.daresay.gitwatch.presentation.screens.chart.ChartScreen
import co.daresay.gitwatch.presentation.screens.settings.SettingsScreen

const val CHART = "chart"
const val SETTINGS = "settings"

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = CHART) {
        composable(SETTINGS) { SettingsScreen(navController) }
        composable(CHART) { ChartScreen(navController) }
    }
}
