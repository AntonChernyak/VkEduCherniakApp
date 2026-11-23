package com.example.vkeducherniakapp.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.vkeducherniakapp.R
import com.example.vkeducherniakapp.presentation.ui.theme.VkEduCherniakAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = { Text("Каталог приложений", color = MaterialTheme.colorScheme.onPrimary) },
        navigationIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(R.string.app_icon),
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = 12.dp, end = 8.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
    )
}

@Preview
@Composable
fun AppToolbarPreview() {
    VkEduCherniakAppTheme {
        AppToolbar()
    }
}