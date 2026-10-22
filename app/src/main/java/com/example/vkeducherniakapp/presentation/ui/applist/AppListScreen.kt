package com.example.vkeducherniakapp.presentation.ui.applist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListItem

@Composable
fun AppListScreen(
    apps: List<AppListItem>, modifier: Modifier = Modifier,
    onAppClick: (AppListItem) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = apps,
            key = { app -> app.id }
        ) { app ->
            AppListItem(app = app)
        }
    }
}