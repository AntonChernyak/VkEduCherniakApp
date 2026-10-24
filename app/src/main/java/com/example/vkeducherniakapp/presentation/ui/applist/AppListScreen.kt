package com.example.vkeducherniakapp.presentation.ui.applist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vkeducherniakapp.R
import com.example.vkeducherniakapp.domain.model.AppCategory
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListContent
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListError
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListEvent
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListLoader
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListState
import com.example.vkeducherniakapp.presentation.ui.components.AppToolbar
import com.example.vkeducherniakapp.presentation.ui.theme.VkEduCherniakAppTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun AppListScreen(
    apps: List<AppListItem>,
    onAppClick: (AppListItem) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AppListViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    val events = viewModel.events

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveEvents(
        events = events,
        snackbarHostState = snackbarHostState,
    )


    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AppToolbar(
                modifier = Modifier.clickable { viewModel.onToolbarClick() }
            )
        }
    ) { innerPadding ->
        when (val currentState = state) {
            is AppListState.Loading -> {
                AppListLoader(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            is AppListState.Error -> {
                AppListError(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }

            is AppListState.Content -> {
                AppListContent(
                    apps = currentState.apps,
                    onAppClick = onAppClick,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun ObserveEvents(
    events: Flow<AppListEvent>,
    snackbarHostState: SnackbarHostState,
) {
    val thisIsToolbar = stringResource(R.string.this_is_toolbar)

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is AppListEvent.ShowToolbarMessage -> {
                    snackbarHostState.showSnackbar(thisIsToolbar)
                }
            }
        }
    }
}

@Preview
@Composable
fun AppListScreenPreview() {
    VkEduCherniakAppTheme {
        AppListScreen(
            apps = listOf(
                AppListItem(
                    id = 1,
                    title = "Telegram",
                    category = AppCategory.SOCIAL,
                    imageUrl = "",
                    description = "Быстрый и защищенный мессенджер"
                ),
                AppListItem(
                    id = 2,
                    title = "Яндекс Музыка",
                    category = AppCategory.MUSIC,
                    imageUrl = "",
                    description = "Треки, подкасты и плейлисты"
                )
            ),
            onAppClick = {}
        )
    }
}