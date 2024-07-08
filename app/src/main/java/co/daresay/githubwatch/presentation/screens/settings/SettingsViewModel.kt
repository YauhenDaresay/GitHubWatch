package co.daresay.githubwatch.presentation.screens.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.daresay.githubwatch.data.settings.SettingsRepository
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository) : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set

    init {
        fetchSettings()
    }

    private fun fetchSettings() {
        viewModelScope.launch {
            uiState = uiState.copy(userName = settingsRepository.getUserName())
        }
    }

    fun onUserNameChanged(userName: String) {
        uiState = uiState.copy(userName = userName)
    }

    fun onSaveSettings() {
        viewModelScope.launch {
            settingsRepository.setUserName(uiState.userName)
            uiState = uiState.copy(onSaved = true)
        }
    }
}

data class UiState(val userName: String = "", val onSaved: Boolean = false)
