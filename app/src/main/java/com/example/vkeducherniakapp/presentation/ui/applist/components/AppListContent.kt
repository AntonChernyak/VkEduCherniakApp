package com.example.vkeducherniakapp.presentation.ui.applist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vkeducherniakapp.domain.model.AppListItem

@Composable
fun AppListContent(
    apps: List<AppListItem>,
    onAppClick: (AppListItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(
            items = apps,
            key = { app -> app.id }
        ) { app ->
            AppListItem(
                app = app,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAppClick(app) }
            )
        }
    }
}