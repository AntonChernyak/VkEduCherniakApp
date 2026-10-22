package com.example.vkeducherniakapp.presentation.ui.applist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducherniakapp.domain.model.AppCategory
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListItem
import com.example.vkeducherniakapp.presentation.ui.components.AppToolbar
import com.example.vkeducherniakapp.presentation.ui.theme.VkEduCherniakAppTheme

@Composable
fun AppListScreen(
    apps: List<AppListItem>,
    onAppClick: (AppListItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        AppToolbar()

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