package com.example.vkeducherniakapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkeducherniakapp.data.repository.AppListMockRepository
import com.example.vkeducherniakapp.domain.model.AppCategory
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.presentation.ui.applist.AppListScreen
import com.example.vkeducherniakapp.presentation.ui.theme.VkEduCherniakAppTheme

class MainActivity : ComponentActivity() {

    private val apps = AppListMockRepository().getApps()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEduCherniakAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppListScreen(apps, modifier = Modifier.padding(innerPadding))
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
            )
        )
    }
}