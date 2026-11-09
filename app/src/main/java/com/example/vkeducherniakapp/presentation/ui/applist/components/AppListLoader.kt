package com.example.vkeducherniakapp.presentation.ui.applist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkeducherniakapp.presentation.ui.theme.VkEduCherniakAppTheme

@Composable
fun AppListLoader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun Preview() {
    VkEduCherniakAppTheme {
        AppListLoader()
    }
}