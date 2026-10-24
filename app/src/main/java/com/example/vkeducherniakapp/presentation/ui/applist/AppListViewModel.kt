package com.example.vkeducherniakapp.presentation.ui.applist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkeducherniakapp.data.repository.AppListMockRepository
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListError
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListEvent
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.IOException

class AppListViewModel : ViewModel() {

    private val mockRepository = AppListMockRepository()

    private val _state = MutableStateFlow<AppListState>(AppListState.Loading)
    val state = _state.asStateFlow()

    private val _events = Channel<AppListEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    private var loadingJob: Job? = null

    init {
        loadApps()
    }

    fun loadApps() {
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            _state.value = AppListState.Loading
            try {
                val apps = mockRepository.getApps()
                _state.value = AppListState.Content(apps = apps)
            } catch (e: CancellationException) {
                throw e
            } catch (e: IOException) {
                _state.value = AppListState.Error(AppListError.Network)
            } //catch (e: HttpException) {
            //  _state.value = AppListState.Error(AppError.Server)
            //  }
            catch (e: Exception) {
                _state.value = AppListState.Error(AppListError.Unknown(e.localizedMessage))
                Log.e(APP_LIST_VIEW_MODEL_LOG_TAG, "Failed to load apps: ${e.message}", e)
            }
        }
    }

    fun onToolbarClick() {
        viewModelScope.launch {
            _events.send(AppListEvent.ShowToolbarMessage)
        }
    }

    companion object {
        private const val APP_LIST_VIEW_MODEL_LOG_TAG = "AppListViewModelTag"
    }
}