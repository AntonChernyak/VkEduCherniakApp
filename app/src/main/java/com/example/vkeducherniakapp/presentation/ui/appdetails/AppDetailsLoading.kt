package com.example.vkeducherniakapp.presentation.ui.appdetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkeducherniakapp.presentation.ui.theme.VkEduCherniakAppTheme

@Composable
fun AppDetailsLoading(modifier: Modifier = Modifier) {
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
        AppDetailsLoading(Modifier.fillMaxSize())
    }
}