package com.example.vkeducherniakapp.presentation.ui.appdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkeducherniakapp.R
import com.example.vkeducherniakapp.domain.model.AppCategory
import com.example.vkeducherniakapp.domain.model.AppListItem
import com.example.vkeducherniakapp.presentation.ui.applist.components.AppListItem
import com.example.vkeducherniakapp.presentation.ui.theme.VkEduCherniakAppTheme

@Composable
fun AppShowcase(
    apps: List<AppListItem>,
    onAppClick: (AppListItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pages = apps.chunked(3)
    val pagerState = rememberPagerState { pages.size }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.showcase),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(8.dp))

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) { pageIndex ->
            val pageApps = pages[pageIndex]

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                pageApps.forEach { app ->
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
}

@Preview
@Composable
fun AppShowcasePreview() {
    val mockShowcaseApps = List(9) { index ->
        AppListItem(
            id = index + 1,
            title = "Приложение витрины #${index + 1}",
            category = if (index % 2 == 0) AppCategory.GAMES else AppCategory.SOCIAL,
            imageUrl = "",
            description = "Описание рекомендованного приложения номер ${index + 1}"
        )
    }

    VkEduCherniakAppTheme {
        AppShowcase(
            apps = mockShowcaseApps,
            onAppClick = {},
        )
    }
}
